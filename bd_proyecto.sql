-- --------------------------------------------------------
-- Host:                         dpg-ct2u0dqj1k6c73b118og-a.oregon-postgres.render.com
-- Versión del servidor:         PostgreSQL 16.4 (Debian 16.4-1.pgdg120+2) on x86_64-pc-linux-gnu, compiled by gcc (Debian 12.2.0-14) 12.2.0, 64-bit
-- SO del servidor:              
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla public.comentarios
CREATE TABLE IF NOT EXISTS "comentarios" (
	"id" BIGINT NOT NULL,
	"comentario" VARCHAR(255) NULL DEFAULT NULL,
	"fecha_coment" TIMESTAMP NULL DEFAULT NULL,
	"id_jugador" BIGINT NULL DEFAULT NULL,
	"id_usu" BIGINT NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fknq571wxb0sh2f9l1wmqlu26k2" FOREIGN KEY ("id_jugador") REFERENCES "jugadores" ("id") ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT "fkqdn66ge279bba8fr51owj2vyu" FOREIGN KEY ("id_usu") REFERENCES "usuarios" ("id") ON UPDATE NO ACTION ON DELETE CASCADE
);

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla public.evento
CREATE TABLE IF NOT EXISTS "evento" (
	"id" BIGINT NOT NULL,
	"descripcion" VARCHAR(255) NULL DEFAULT NULL,
	"fecha_fin_evento" TIMESTAMP NULL DEFAULT NULL,
	"fecha_ini_evento" TIMESTAMP NULL DEFAULT NULL,
	"foto_evento" VARCHAR(255) NULL DEFAULT NULL,
	"ganador" BIGINT NULL DEFAULT NULL,
	"id_jugador" BIGINT NOT NULL,
	"nombre" VARCHAR(255) NULL DEFAULT NULL,
	"premio" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkaers1jrs76hmn6glrnr9pocca" FOREIGN KEY ("id_jugador") REFERENCES "jugadores" ("id") ON UPDATE NO ACTION ON DELETE CASCADE
);

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla public.jugadores
CREATE TABLE IF NOT EXISTS "jugadores" (
	"id" BIGINT NOT NULL,
	"fecha_nac" VARCHAR(255) NULL DEFAULT NULL,
	"foto" VARCHAR(255) NULL DEFAULT NULL,
	"juegos" VARCHAR(255) NULL DEFAULT NULL,
	"nombre" VARCHAR(255) NULL DEFAULT NULL,
	"nombre_jugador" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla public.merchandising
CREATE TABLE IF NOT EXISTS "merchandising" (
	"id" BIGINT NOT NULL,
	"cantidad" INTEGER NULL DEFAULT NULL,
	"foto" VARCHAR(255) NULL DEFAULT NULL,
	"nombre" VARCHAR(255) NULL DEFAULT NULL,
	"precio" DOUBLE PRECISION NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla public.participacion
CREATE TABLE IF NOT EXISTS "participacion" (
	"id" BIGINT NOT NULL,
	"fecha_part" TIMESTAMP NULL DEFAULT NULL,
	"id_evento" BIGINT NOT NULL,
	"id_usu" BIGINT NOT NULL,
	"num_part" INTEGER NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkoldg7n6y0wig2xomp8y72dhqe" FOREIGN KEY ("id_evento") REFERENCES "evento" ("id") ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT "fkrj7xi6211ps4pht1hxicyneei" FOREIGN KEY ("id_usu") REFERENCES "usuarios" ("id") ON UPDATE NO ACTION ON DELETE CASCADE
);

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla public.usuarios
CREATE TABLE IF NOT EXISTS "usuarios" (
	"id" BIGINT NOT NULL,
	"dinero" DOUBLE PRECISION NOT NULL,
	"email" VARCHAR(255) NULL DEFAULT NULL,
	"fecha_fin_sub" TIMESTAMP NULL DEFAULT NULL,
	"fecha_fin_usu" TIMESTAMP NULL DEFAULT NULL,
	"fecha_ini_sub" TIMESTAMP NULL DEFAULT NULL,
	"fecha_ini_usu" TIMESTAMP NULL DEFAULT NULL,
	"fecha_nac" VARCHAR(255) NULL DEFAULT NULL,
	"file" VARCHAR(255) NULL DEFAULT NULL,
	"nombre_completo" VARCHAR(255) NULL DEFAULT NULL,
	"password" VARCHAR(255) NULL DEFAULT NULL,
	"sub" BOOLEAN NOT NULL,
	"tarifa" DOUBLE PRECISION NOT NULL,
	"tarjeta_credito" VARCHAR NOT NULL,
	"username" VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY ("id")
);

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
