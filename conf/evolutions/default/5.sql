-- !Ups
DROP TABLE Game;
CREATE TABLE Game
(
    game_id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    completed    BOOLEAN NOT NULL DEFAULT FALSE,
    state        TEXT,
    road_manager INTEGER NOT NULL,
    home_manager INTEGER NOT NULL,
    KEY completed (completed),
    KEY road_manager (road_manager),
    KEY home_manager (home_manager)
);
-- !Down
DROP TABLE Game;
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
