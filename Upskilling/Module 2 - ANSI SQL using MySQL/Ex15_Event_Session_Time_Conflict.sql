SELECT
    a.event_id,
    e.title AS event_title,
    a.session_id  AS session_a_id,
    a.title  AS session_a_title,
    a.start_time AS session_a_start,
    a.end_time AS session_a_end,
    b.session_id   AS session_b_id,
    b.title    AS session_b_title,
    b.start_time   AS session_b_start,
    b.end_time   AS session_b_end
FROM Sessions a
JOIN Sessions b  ON a.event_id   = b.event_id
               AND a.session_id  < b.session_id   -- avoid duplicates & self-join
JOIN Events   e  ON a.event_id   = e.event_id
WHERE a.start_time < b.end_time              -- overlap condition
  AND a.end_time   > b.start_time
ORDER BY a.event_id, a.session_id;