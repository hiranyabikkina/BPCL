--
-- Table structure for table `coupon_inspections`
--

CREATE TABLE `coupon_inspections` (
  `uuid` varchar(255) NOT NULL,
  `plant_location` varchar(255) NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `coupon_size` varchar(255) NOT NULL,
  `lot_code` varchar(255) DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `coupon_production`
--

CREATE TABLE `coupon_production` (
  `uuid` varchar(50) NOT NULL,
  `coupon_quantity` int NOT NULL,
  `lot_code_quantity` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `line_information`
--

CREATE TABLE `line_information` (
  `uuid` varchar(50) NOT NULL,
  `loc_code` int NOT NULL,
  `line_id` varchar(50) DEFAULT NULL,
  `line_no` varchar(255) DEFAULT NULL,
  `line_name` varchar(255) DEFAULT NULL,
  `equipment_type` varchar(255) DEFAULT NULL,
  `brand_oem_name` varchar(255) DEFAULT NULL,
  `model_no` varchar(255) DEFAULT NULL,
  `serial_no` varchar(255) DEFAULT NULL,
  `mac_id` varchar(255) DEFAULT NULL,
  `installation_date` varchar(255) DEFAULT NULL,
  `ip_add` varchar(255) DEFAULT NULL,
  `port_no` int DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `production_lines`
--

CREATE TABLE `production_lines` (
  `uuid` varchar(255) NOT NULL,
  `plant_location` varchar(255) NOT NULL,
  `line_identity` varchar(255) NOT NULL,
  `line_name` varchar(255) NOT NULL,
  `ip_printer_address` varchar(255) NOT NULL,
  `ip_printer_port` varchar(255) NOT NULL,
  `hw_type` bigint NOT NULL,
  `is_having_diverter` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `production_lines`
--
CREATE TABLE `printer_status` (
  `uuid` int NOT NULL DEFAULT '9000',
  `message` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `issued` int NOT NULL,
  `print` int NOT NULL,
  `inspect` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `printer_status`
--

INSERT INTO `printer_status` (`uuid`, `message`, `size`, `issued`, `print`, `inspect`) VALUES
(9000, 'Response: Printer Continues print mode!!!', '16x25', 1000, 0, 0);
--
-- Indexes for dumped tables
--
--
-- Indexes for table `coupon_inspections`
--
ALTER TABLE `coupon_inspections`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `line_information`
--
ALTER TABLE `line_information`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `production_lines`
--
ALTER TABLE `production_lines`
  ADD PRIMARY KEY (`uuid`),
  ADD KEY `production_lines_fk0` (`plant_location`);
COMMIT;

