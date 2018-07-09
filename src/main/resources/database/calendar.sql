/*
 * 生成一个连续的日历表    2005-1-1 ———— 2278-10-16
 */

DROP TABLE IF EXISTS `num`;
CREATE TABLE num (i INT);-- 创建一个表用来储存0-9的数字
INSERT INTO num (i) VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);-- 生成0-9的数字，方便以后计算时间

DROP TABLE IF EXISTS `calendar`;
CREATE TABLE  IF NOT EXISTS calendar(datelist DATE); -- 生成一个存储日期的表，datalist是字段名

-- 这里是生成并插入日期数据
INSERT INTO calendar(datelist) SELECT
    ADDDATE(
        (   -- 这里的起始日期，你可以换成当前日期
            DATE_FORMAT("2005-1-1", '%Y-%m-%d') 
        ),
        numlist.id
    ) AS `date`
FROM
    (
        SELECT
            n1.i + n10.i * 10 + n100.i * 100 + n1000.i * 1000+ n10000.i * 10000 AS id
        FROM
            num n1
        CROSS JOIN num AS n10
        CROSS JOIN num AS n100
        CROSS JOIN num AS n1000
        CROSS JOIN num AS n10000
    ) AS numlist;
    
DROP TABLE IF EXISTS `num`;