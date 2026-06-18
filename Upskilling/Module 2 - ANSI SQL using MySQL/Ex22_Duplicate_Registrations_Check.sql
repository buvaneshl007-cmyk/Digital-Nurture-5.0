SELECT
    r.user_id,
    u.full_name,
    r.event_id,
    e.title             AS event_title,
    COUNT(r.registration_id) AS registration_count
FROM Registrations r
JOIN Users  u ON r.user_id  = u.user_id
JOIN Events e ON r.event_id = e.event_id
GROUP BY r.user_id, u.full_name, r.event_id, e.title
HAVING COUNT(r.registration_id) > 1
ORDER BY registration_count DESC;