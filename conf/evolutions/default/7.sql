-- !Ups
ALTER TABLE Team CHANGE COLUMN abbrev abbrev varchar(3) NOT NULL;
-- !Downs
ALTER TABLE Team CHANGE COLUMN abbrev abbrev varchar(3);
