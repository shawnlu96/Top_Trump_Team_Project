--create schema toptrumps; --creates the schema that shall be used if default public schema is taken 

create table games 
(
	game_id int primary key, 
	winner_player int not null check (0<winner_player and winner_player<6), --we can have maximum 5 players
	draws int not null, --assume at least 0 draws	
	rounds int not null --assume at least 0 rounds
)

create table players
(
	player_id int check (0<player_id and player_id<6), --we can have maximum 5 players
	game_id int references games on delete cascade on update cascade, --referential integrity for game ids
	rounds_won int not null, --assume at least 0 rounds won by each player 
	primary key (player_id, game_id) --composite primary key 
)

insert into games values (0,1,0,0); --initial insert values
insert into players values (1,0,0);
insert into players values (2,0,0);
