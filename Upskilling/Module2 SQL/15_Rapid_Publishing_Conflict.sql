-- 15. Rapid Publishing Conflict
SELECT l1.course_id, l1.title AS lesson1, l2.title AS lesson2, l1.publish_time
FROM Lessons l1
JOIN Lessons l2 ON l1.course_id = l2.course_id 
                AND l1.publish_time = l2.publish_time 
                AND l1.lesson_id < l2.lesson_id;