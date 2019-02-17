--Here are all the sql statements used beside those involved in creating the database/schema 

--select statements for statistics 

select count(game_id) as games_played from games; --number of games played overall 

select count(winner_player) as ai_wins from games where winner_player <> 1; --times the computer won 

select count(winner_player) as human_wins from games where winner_player = 1; --times the human won

select floor(avg(draws)) as avg_draws from games; --average number of draws 

select max(rounds) as max_rounds from games; --max rounds per game 

--insert statements at the end of each game 

insert into games values (int,int,int,int); --insert int with releavant values for games table

insert into players values (int,int,int); --insert int with releavant values for players table