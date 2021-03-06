package com.company.day3.lab01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class SomeRepositoryImpl implements SomeRepository {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveGuest(Guest guest) {
        init();
        String query = "insert into guest (id, g_name) values (? , ?)";
        jdbcTemplate.update(query, guest.getId(),
                guest.getName());
    }

    @Override
    public Event getEvent(Long id) {
        init();

        String query = "SELECT * FROM EVENT WHERE ID = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(
                query, new Object[]{id}, (rs, rowNum) -> {
                    Event event1 = new Event();
                    event1.setId(rs.getLong("ID"));
                    event1.setName(rs.getString("E_NAME"));
                    return event1;
                });
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    @Override
    public List<Guest> getGuests() {
        init();

        String query = "SELECT * FROM GUEST";
        List<Guest> guests = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        for (Map row : rows) {
            Guest guest = new Guest();
            guest.setId(UUID.fromString(String.valueOf(row.get("ID"))));
            guest.setName((String) row.get("G_NAME"));
            guests.add(guest);
        }
        return guests;

    }

    @Override
    public Guest findByName(String name) {
        init();

        String query = "SELECT * FROM GUEST WHERE G_NAME = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(
                query, new Object[]{name}, (rs, rowNum) -> {
                    Guest guest = new Guest();
                    guest.setId(UUID.fromString(rs.getString("ID")));
                    guest.setName(rs.getString("G_NAME"));
                    return guest;
                });
    }

    @Override
    public void run() {
        init();

        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table guest if exists");
        jdbcTemplate.execute("drop table event if exists");
        jdbcTemplate.execute("drop table orders if exists");
        jdbcTemplate.execute("create table guest(" +
                "id uuid, g_name varchar(255))");
        jdbcTemplate.execute("create table event(" +
                "id uuid, e_name varchar(255), address varchar(255))");
        jdbcTemplate.execute("create table orders(" +
                "id uuid, o_name varchar(255), description varchar(255))");
        System.out.println("Tables created successfully");
        System.out.println("**********");

    }

    @Override
    public void saveEvent(Event event) {
        init();
        String query = "insert into event (id, e_name) values (? , ?)";
        jdbcTemplate.update(query, event.getId(),
                event.getName());
    }
}