
--
-- Database: `bpcl_pails`
--

-- --------------------------------------------------------

--
-- Table structure for table `bottle_inspection`
--

CREATE TABLE `bottle_inspection` (
  `uuid` varchar(255) NOT NULL,
  `bottle_code` varchar(255) NOT NULL,
  `plant_location` varchar(255) NOT NULL,
  `line_uuid` varchar(255) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `coupon_inspection`
--

CREATE TABLE `coupon_inspection` (
  `uuid` bigint NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `coupon_size` varchar(255) NOT NULL,
  `lot_code` varchar(255) DEFAULT NULL,
  `plant_location` varchar(255) DEFAULT NULL,
  `line_uuid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `line_information`
--

CREATE TABLE `line_information` (
  `uuid` varchar(50) NOT NULL,
  `loc_code` int NOT NULL,
  `line_no` varchar(255) DEFAULT NULL,
  `line_name` varchar(255) DEFAULT NULL,
  `equipment_type` varchar(255) DEFAULT NULL,
  `brand_oem_name` varchar(255) DEFAULT NULL,
  `model_no` varchar(65) DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `mac_id` varchar(255) DEFAULT NULL,
  `installation_date` varchar(255) DEFAULT NULL,
  `ip_add` varchar(255) DEFAULT NULL,
  `port_no` int DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `pails_production`
--

CREATE TABLE `pails_production` (
  `uuid` varchar(255) NOT NULL,
  `plan_id` varchar(255) NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `bottle_code` varchar(255) NOT NULL,
  `line_id` varchar(255) NOT NULL,
  `is_coupon_apply` int NOT NULL,
  `pack_type` varchar(65) DEFAULT NULL,
  `status` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `production_plan`
--

CREATE TABLE `production_plan` (
  `uuid` varchar(255) NOT NULL,
  `plan_id` int DEFAULT NULL,
  `loc_code` int NOT NULL,
  `plant_location` varchar(255) NOT NULL,
  `line_uuid` varchar(255) NOT NULL,
  `plan_date` varchar(255) DEFAULT NULL,
  `product_code` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `priority` bigint NOT NULL DEFAULT '1',
  `pack_type` varchar(255) DEFAULT NULL,
  `size_code` varchar(255) NOT NULL,
  `units_per_case` bigint NOT NULL,
  `purpose` varchar(255) NOT NULL,
  `volume` int NOT NULL,
  `mrp` double NOT NULL,
  `mrp_batch` varchar(255) NOT NULL,
  `product_batch` varchar(255) NOT NULL,
  `is_coupon_apply` varchar(55) NOT NULL DEFAULT '0',
  `create_time` timestamp NULL DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Indexes for table `bottle_inspection`
--
ALTER TABLE `bottle_inspection`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `coupon_inspection`
--
ALTER TABLE `coupon_inspection`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `line_information`
--
ALTER TABLE `line_information`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `pails_production`
--
ALTER TABLE `pails_production`
  ADD PRIMARY KEY (`uuid`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indexes for table `production_plan`
--
ALTER TABLE `production_plan`
  ADD PRIMARY KEY (`uuid`),
  ADD UNIQUE KEY `plan_id` (`plan_id`),
  ADD KEY `production_plan_fk0` (`plant_location`),
  ADD KEY `production_plan_fk1` (`line_uuid`);



--
-- Table structure for table `pails_production_scanner`
--

CREATE TABLE `pails_production_scanner` (
  `uuid` int(11) NOT NULL,
  `bottle_code` varchar(255) DEFAULT NULL,
  `coupon_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pails_production_scanner`
--

INSERT INTO `pails_production_scanner` (`uuid`, `bottle_code`, `coupon_code`) VALUES
(1, '', '');

ALTER TABLE `pails_production_scanner`
  ADD PRIMARY KEY (`uuid`);
