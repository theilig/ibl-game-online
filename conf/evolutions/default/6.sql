-- !Ups
ALTER TABLE Team ADD COLUMN abbrev varchar(3);
-- !Downs
ALTER TABLE Team DROP COLUMN abbrev;
