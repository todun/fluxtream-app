CREATE TABLE `Facet_MymeeObservation` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`api` int(11) NOT NULL,
`comment` longtext,
`end` bigint(20) NOT NULL,
`fullTextDescription` longtext,
`guestId` bigint(20) NOT NULL,
`isEmpty` char(1) NOT NULL,
`objectType` int(11) NOT NULL,
`start` bigint(20) NOT NULL,
`tags` longtext,
`timeUpdated` bigint(20) NOT NULL,
`note` longtext,
`mymeeId` varchar(255) NOT NULL,
`name` varchar(255),
`user` varchar(255),
`timezoneOffset` int NOT NULL,
`amount` double,
`baseAmount` int,
`unit` varchar(255),
`baseUnit` varchar(255),
`imageURL` varchar(255),
`longitude` double,
`latitude` double,
PRIMARY KEY (`id`),
KEY `isEmpty_index` (`isEmpty`),
KEY `end_index` (`end`),
KEY `start_index` (`start`),
KEY `api_index` (`api`),
KEY `name` (`name`),
KEY `objectType_index` (`objectType`),
KEY `guestId_index` (`guestId`),
KEY `timeUpdated_index` (`timeUpdated`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;