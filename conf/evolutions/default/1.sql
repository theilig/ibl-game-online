-- !Ups
CREATE TABLE Game (
    game_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    road_team_id INTEGER NOT NULL,
    home_team_id INTEGER NOT NULL,
    road_score INTEGER NOT NULL,
    home_score INTEGER NOT NULL,
    inning INTEGER,
    half_inning ENUM("Top", "Bottom"),
    outs INTEGER,
    runner_on_first INTEGER,
    runner_on_second INTEGER,
    runner_on_third INTEGER,
    batter INTEGER,
    pitcher INTEGER
);

CREATE TABLE Team (
    team_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    geographic_location_id INTEGER NOT NULL,
    home_park_id INTEGER NOT NULL
);

CREATE TABLE Player (
    player_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (100) NOT NULL,
    pitcher_id INTEGER,
    bats ENUM("Left", "Right", "Both") NOT NULL,
    durability INTEGER NOT NULL,
    injury_days INTEGER NOT NULL,
    running INTEGER NOT NULL,
    stealing INTEGER NOT NULL,
    slash_left VARCHAR(40) NOT NULL,
    slash_right VARCHAR(40) NOT NULL,
    template_id_left INTEGER NOT NULL,
    template_id_right INTEGER NOT NULL,
    results_left VARCHAR(200) NOT NULL,
    results_right VARCHAR(200) NOT NULL,
    ifr_location VARCHAR(30) NOT NULL,
    ofr_location VARCHAR(30) NOT NULL,
    df_location VARCHAR(30) NOT NULL,
    power VARCHAR(30) NOT NULL,
    jump INTEGER NOT NULL,
    clutch ENUM("Tough", "Normal") NOT NULL DEFAULT "Normal",
    `usage` VARCHAR(100) NOT NULL
 );

CREATE TABLE Pitcher (
    pitcher_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    throws ENUM("Both", "Left", "Right") NOT NULL,
    slash_left VARCHAR(40) NOT NULL,
    slash_right VARCHAR(40) NOT NULL,
    template_id_left INTEGER NOT NULL,
    template_id_right INTEGER NOT NULL,
    results_left VARCHAR(200) NOT NULL,
    results_right VARCHAR(200) NOT NULL,
    fatigue_results_left VARCHAR(200) NOT NULL,
    fatigue_results_right VARCHAR(200) NOT NULL,
    balk ENUM("Prone", "Common", "Unlikely", "Rare") NOT NULL,
    hold ENUM("Excellent", "Very Good", "Average", "Fair", "Poor") NOT NULL,
    stamina_slash VARCHAR(50) NOT NULL,
    stamina_starting INTEGER NOT NULL,
    stamina_relieving INTEGER NOT NULL,
    stamina_old varchar(50) NOT NULL,
    durability INTEGER NOT NULL,
    injury_days INTEGER NOT NULL,
    `usage` VARCHAR(60) NOT NULL
);

CREATE TABLE ResultTemplate (
    result_template_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    template_id INTEGER NOT NULL,
    rank INTEGER NOT NULL,
    result ENUM("!", "E", "L", "PARK?", "1B", "3B", "HG-", "HG", "2B", "RG", "HF", "HR", "HB", "BB", "SO", "RG+",
                "P", "WP & PB", "CFR", "OFR", "IFR", "SG", "DF", "3B?"
                ) NOT NULL,
    `location` ENUM("lcf", "lc", "rc", "cf", "*", "inf", "gcf", "ss", "2b", "3b", "1b", "rcf", "llf", "lrf",
    "cfw", "lfl", "rfl", "lfw", "rfw", "lf", "rf", "glf", "grf"),
    UNIQUE KEY slot (template_id, rank)
);

CREATE TABLE Park (
    park_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    surface ENUM("Grass", "Turf", "Slow Grass") NOT NULL,
    quality ENUM("Excellent", "Very Good", "Average", "Fair", "Poor") NOT NULL,
    foul_territory ENUM("Very Small", "Small", "Average", "Large", "Very Large") NOT NULL,
    ifr_adjustment INTEGER NOT NULL,
    ofr_adjustment INTEGER NOT NULL,
    template_id INTEGER NOT NULL,
    results VARCHAR(200) NOT NULL
);

CREATE TABLE GeographicLocation (
    geographic_location_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Wind (
    wind_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    park_id INTEGER NOT NULL,
    game_month ENUM("April", "May", "June", "July", "August", "September", "October") NOT NULL,
    wind_type ENUM("no wind", "cross", "straight") NOT NULL,
    rank INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    UNIQUE KEY park_month_wind (park_id, game_month, wind_type),
    KEY ordering (park_id, game_month, rank)
);

CREATE TABLE ParkEffect (
    park_effect_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    park_id INTEGER NOT NULL,
    effect ENUM("Roof", "Elevation"),
    UNIQUE KEY park_effect (park_id, effect)
)

CREATE TABLE Distance (
    distance_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    park_id INTEGER NOT NULL,
    location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL"),
    wind_type ENUM("no wind", "cross", "straight") NOT NULL,
    fence_distance INTEGER NOT NULL,
    UNIQUE KEY park_wind_location (park_id, wind_type, location)
);

CREATE TABLE FenceHeight (
    fence_height_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    park_id INTEGER NOT NULL,
    location ENUM("LFL", "LF", "LCF", "CF", "RCF", "RF", "RFL"),
    fence_height INTEGER NOT NULL,
    UNIQUE KEY park_fence_height_location (park_id, fence_height, location)
);

CREATE TABLE Temperature (
    temperature_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    geographic_location_id INTEGER NOT NULL,
    time_of_day ENUM("Day", "Night") NOT NULL,
    game_month ENUM("April", "May", "June", "July", "August", "September", "October") NOT NULL,
    temp ENUM("Hot", "Warm", "Cool", "Cold"),
    rank INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    UNIQUE KEY loc_tod_month_temp (geographic_location_id, time_of_day, game_month, temp),
    KEY loc_tod_month_ordering (geographic_location_id, time_of_day, game_month, rank)
);

CREATE TABLE CloudCover (
    cloud_cover_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    geographic_location_id INTEGER NOT NULL,
    game_month ENUM("April", "May", "June", "July", "August", "September", "October") NOT NULL,
    cover ENUM("Clear", "Partly Cloudy", "Cloudy"),
    rank INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    UNIQUE KEY loc_month_cover (geographic_location_id, game_month, cover),
    KEY loc_month_ordering (geographic_location_id, game_month, rank)
);

CREATE TABLE Precipitation (
    precipitation_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    geographic_location_id INTEGER NOT NULL,
    game_month ENUM("April", "May", "June", "July", "August", "September", "October") NOT NULL,
    precip ENUM("Thunderstorms", "Showers", "Snow", "Fog", "None") NOT NULL,
    rank INTEGER NOT NULL,
    frequency INTEGER NOT NULL,
    UNIQUE KEY loc_month_precip (geographic_location_id, game_month, precip),
    KEY loc_month_ordering (geographic_location_id, game_month, rank)
);

CREATE TABLE Roster (
    roster_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    team_id INTEGER NOT NULL,
    player_id INTEGER NOT NULL,
    UNIQUE KEY team_player (team_id, player_id)
);

CREATE TABLE Defense (
    defense_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    player_id INTEGER NOT NULL,
    `position` ENUM ('P', 'C', '1B', '2B', '3B', 'SS', 'LF', 'CF', 'RF') NOT NULL,
    `primary` BOOLEAN NOT NULL,
    error INTEGER NOT NULL,
    rating VARCHAR(2) NOT NULL,
    pivot CHAR(1),
    throws INTEGER,
    lost_pitch ENUM("Excellent", "Very Good", "Average", "Fair", "Poor", "Rare", "Unlikely", "Normal", "Prone", "Prone*"),
    stop_jump INTEGER,
    UNIQUE KEY player_position (player_id, `position`)
);

CREATE TABLE Bunting (
    bunting_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    player_id INTEGER NOT NULL,
    in_play INTEGER NOT NULL,
    placement ENUM("Excellent", "Very Good", "Average", "Fair", "Poor") NOT NULL,
    speed INTEGER NOT NULL,
    UNIQUE KEY player_bunts (player_id)
);

CREATE TABLE WildRating (
    wild_rating_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    player_id INTEGER NOT NULL,
    rating ENUM("SUSP", "HOT", "LAW", "GEEZER", "TEST") NOT NULL,
    games INTEGER,
    UNIQUE KEY player_rating (player_id, rating)
);

CREATE TABLE User (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    password_hash VARCHAR(200) NOT NULL,
    email VARCHAR(256) NOT NULL,
    confirmed BOOLEAN DEFAULT 0,
    UNIQUE KEY user_email (email)
);

CREATE TABLE UserConfirmation (
    user_confirmation_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(256) NOT NULL,
    user_id INTEGER NOT NULL,
    UNIQUE KEY confirmation_user (user_id),
    UNIQUE KEY confirmation_token (token)
);

CREATE TABLE Tokens (
    token_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    expires INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    token_value VARCHAR(256) NOT NULL,
    UNIQUE KEY token_value (token_value),
    UNIQUE KEY user_id (user_id)
);


-- !Downs
DROP TABLE Game;
DROP TABLE Team;
DROP TABLE Player;
DROP TABLE Pitcher;
DROP TABLE ResultTemplate;
DROP TABLE Park;
DROP TABLE GeographicLocation;
DROP TABLE Temperature;
DROP TABLE CloudCover;
DROP TABLE Precipitation;
DROP TABLE Roster;
DROP TABLE Wind;
DROP TABLE Distance;
DROP TABLE FenceHeight;
DROP TABLE ParkEffect;
DROP TABLE Defense;
DROP TABLE Bunting;
DROP TABLE User;
DROP TABLE UserConfirmation;
DROP TABLE Tokens;
