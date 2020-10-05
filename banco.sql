create database tabela;
use tabela;


create table jogo (id_jogo int not null auto_increment primary key, 
	jg_placar int(4) not null,
    jg_pts_max int(4) not null, 
	jg_pts_min int(4) not null, 
    jg_pts_recMax int(4) not null, 
    jg_pts_recMin int(4) not null
);

select * from jogo;