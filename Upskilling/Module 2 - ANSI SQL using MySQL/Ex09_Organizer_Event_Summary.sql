SELECT
    u.user_id                                                           AS organizer_id,
    u.full_name                                                         AS organizer_name,
    COUNT(e.event_id)                                                   AS total_events,
    SUM(CASE WHEN e.status = 'upcoming'   THEN 1 ELSE 0 END)           AS upcoming_events,
    SUM(CASE WHEN e.status = 'completed'  THEN 1 ELSE 0 END)           AS completed_events,
    SUM(CASE WHEN e.status = 'cancelled'  THEN 1 ELSE 0 END)           AS cancelled_events
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name
ORDER BY total_events DESC;