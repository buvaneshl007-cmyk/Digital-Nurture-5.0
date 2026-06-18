-- Ex10_Feedback_Gap.sql
-- Identify events that had registrations but received no feedback at all.

SELECT
    e.event_id,
    e.title,
    e.status,
    COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id FROM Feedback
)
GROUP BY e.event_id, e.title, e.status
ORDER BY total_registrations DESC;