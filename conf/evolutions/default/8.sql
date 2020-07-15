-- !Ups
ALTER TABLE ParkEffect CHANGE COLUMN effect effect ENUM("Roof", "Elevation") NOT NULL;
ALTER TABLE Distance CHANGE COLUMN location location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL") NOT NULL;
ALTER TABLE FenceHeight CHANGE COLUMN location location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL") NOT NULL;
ALTER TABLE Temperature CHANGE COLUMN temp temp ENUM("Hot", "Warm", "Cool", "Cold") NOT NULL;
ALTER TABLE CloudCover CHANGE COLUMN cover cover ENUM("Clear", "Partly Cloudy", "Cloudy") NOT NULL;
-- !Downs
ALTER TABLE ParkEffect CHANGE COLUMN effect effect ENUM("Roof", "Elevation");
ALTER TABLE Distance CHANGE COLUMN location location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL");
ALTER TABLE FenceHeight CHANGE COLUMN location location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL");
ALTER TABLE Temperature CHANGE COLUMN temp temp ENUM("Hot", "Warm", "Cool", "Cold");
ALTER TABLE CloudCover CHANGE COLUMN cover cover ENUM("Clear", "Partly Cloudy", "Cloudy");
