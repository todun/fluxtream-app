CREATE TABLE `Facet_MisfitActivitySession` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api` int(11) NOT NULL,
  `apiKeyId` bigint(20) DEFAULT NULL,
  `comment` longtext,
  `end` bigint(20) NOT NULL,
  `fullTextDescription` longtext,
  `guestId` bigint(20) NOT NULL,
  `isEmpty` char(1) NOT NULL,
  `objectType` int(11) NOT NULL,
  `start` bigint(20) NOT NULL,
  `tags` longtext,
  `timeUpdated` bigint(20) NOT NULL,
  `activityType` varchar(255) DEFAULT NULL,
  `calories` float NOT NULL,
  `distance` float NOT NULL,
  `misfitId` varchar(255) DEFAULT NULL,
  `points` float NOT NULL,
  `steps` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `isEmpty_index` (`isEmpty`),
  KEY `end_index` (`end`),
  KEY `start_index` (`start`),
  KEY `api_index` (`api`),
  KEY `objectType_index` (`objectType`),
  KEY `guestId_index` (`guestId`),
  KEY `activityType` (`activityType`),
  KEY `apiKey` (`apiKeyId`),
  KEY `timeUpdated_index` (`timeUpdated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Facet_MisfitActivitySummary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api` int(11) NOT NULL,
  `apiKeyId` bigint(20) DEFAULT NULL,
  `comment` longtext,
  `end` bigint(20) NOT NULL,
  `fullTextDescription` longtext,
  `guestId` bigint(20) NOT NULL,
  `isEmpty` char(1) NOT NULL,
  `objectType` int(11) NOT NULL,
  `start` bigint(20) NOT NULL,
  `tags` longtext,
  `timeUpdated` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `endTimeStorage` varchar(255) DEFAULT NULL,
  `startTimeStorage` varchar(255) DEFAULT NULL,
  `activityCalories` float NOT NULL,
  `calories` float NOT NULL,
  `distance` float NOT NULL,
  `points` float NOT NULL,
  `steps` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `isEmpty_index` (`isEmpty`),
  KEY `end_index` (`end`),
  KEY `start_index` (`start`),
  KEY `api_index` (`api`),
  KEY `objectType_index` (`objectType`),
  KEY `guestId_index` (`guestId`),
  KEY `date` (`date`),
  KEY `apiKey` (`apiKeyId`),
  KEY `timeUpdated_index` (`timeUpdated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
