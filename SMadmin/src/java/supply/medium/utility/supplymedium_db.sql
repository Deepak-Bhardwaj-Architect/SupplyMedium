drop database if exists supplymedium;
create database supplymedium;
use supplymedium;

CREATE TABLE sm_business_category_master
(
business_category_key bigint auto_increment primary key,
business_category_name varchar(255) 
);

INSERT INTO sm_business_category_master values
(1, 'Aerospace/Defence'),
(2, 'Automobiles and Parts'),
(3, 'Bank/Financial Institution'),
(4, 'Biotechnology'),
(5, 'Cause'),
(6, 'Chemicals'),
(7, 'Church/Religious Organization'),
(8, 'Community Organization'),
(9, 'Company'),
(10, 'Computers/Technology'),
(11, 'Consulting/Business Services'),
(12, 'Education'),
(13, 'Energy/Utility'),
(14, 'Engineering/Construction'),
(15, 'Farming/Agriculture'),
(16, 'Food/Beverages'),
(17, 'Government Organization'),
(18, 'Health/Beauty'),
(19, 'Health/Medical/Pharmaceuticals'),
(20, 'Industrials'),
(21, 'Insurance Company'),
(22, 'Internet/Software'),
(23, 'Legal/Law'),
(24, 'Media/News/Publishing'),
(25, 'Mining/Materials'),
(26, 'Non-Governmental Organization (NGO)'),
(27, 'Non-Profit Organization'),
(28, 'Organization'),
(29, 'Political Organization'),
(30, 'Political Party'),
(31, 'Retail and Consumer Merchandise'),
(32, 'School'),
(33, 'Small Business'),
(34, 'Telecommunication'),
(35, 'Transport/Freight'),
(36, 'Travel/Leisure'),
(37, 'University');

CREATE TABLE  sm_country_master
(
country_key bigint auto_increment primary key,
country_name varchar(255),
country_code varchar(255)
);

insert into sm_country_master values
(1, 'Afghanistan', '93'),
(2, 'Albania', '355'),
(3, 'Algeria', '213'),
(4, 'American Samoa', '1684'),
(5, 'Andorra', '376'),
(6, 'Angola', '244'),
(7, 'Anguilla', '1264'),
(8, 'Antarctica', '6721'),
(9, 'Antigua and Barbuda', '1268'),
(10, 'Argentina', '54'),
(11, 'Armenia', '374'),
(12, 'Aruba', '297'),
(13, 'Ascension', '247'),
(14, 'Australia', '61'),
(15, 'Austria', '43'),
(16, 'Azerbaijan', '994'),
(17, 'Bahamas', '1242'),
(18, 'Bahrain', '973'),
(19, 'Bangladesh', '880'),
(20, 'Barbados', '1246'),
(21, 'Belarus', '375'),
(22, 'Belgium', '32'),
(23, 'Belize', '501'),
(24, 'Benin', '229'),
(25, 'Bermuda', '1441'),
(26, 'Bhutan', '975'),
(27, 'Bolivia', '591'),
(28, 'Bosnia and Herzegovina', '387'),
(29, 'Botswana', '267'),
(30, 'Brazil', '55'),
(31, 'Brunei', '673'),
(32, 'Bulgaria', '359'),
(33, 'Burkina Faso', '226'),
(34, 'Burundi', '257'),
(35, 'Cambodia', '855'),
(36, 'Cameroon', '237'),
(37, 'Canada', '1'),
(38, 'Cape Verde', '238'),
(39, 'Cayman Islands', '1345'),
(40, 'Central African Republic', '236'),
(41, 'Chad', '235'),
(42, 'Chile', '56'),
(43, 'China', '86'),
(44, 'Christmas Island', '618'),
(45, 'Cocos (Keeling) Islands', '618'),
(46, 'Colombia', '57'),
(47, 'Comoros', '269'),
(48, 'Congo', '243'),
(49, 'Cook Islands', '682'),
(50, 'Costa Rica', '506'),
(51, 'C', '225'),
(52, 'Croatia', '385'),
(53, 'Cuba', '53'),
(54, 'Cyprus', '357'),
(55, 'Czech Republic', '420'),
(56, 'Denmark', '45'),
(57, 'Diego Garcia', '246'),
(58, 'Djibouti', '253'),
(59, 'Dominica', '1767'),
(60, 'Dominican Republic', '1809'),
(61, 'East Timor', '670'),
(62, 'Ecuador', '593'),
(63, 'Egypt', '20'),
(64, 'El Salvador', '503'),
(65, 'Equatorial Guinea', '240'),
(66, 'Eritrea', '291'),
(67, 'Estonia', '372'),
(68, 'Ethiopia', '251'),
(69, 'Faeroe Islands', '500'),
(70, 'Falkland Islands', '298'),
(71, 'Fiji', '679'),
(72, 'Finland', '358'),
(73, 'France', '33'),
(74, 'French Guiana', '594'),
(75, 'French Polynesia', '689'),
(76, 'Gabon', '241'),
(77, 'Gambia', '220'),
(78, 'Georgia', '995'),
(79, 'Germany', '49'),
(80, 'Ghana', '233'),
(81, 'Gibraltar', '350'),
(82, 'Greece', '30'),
(83, 'Greenland', '299'),
(84, 'Grenada', '1473'),
(85, 'Guadeloupe', '590'),
(86, 'Guam', '1671'),
(87, 'Guatemala', '502'),
(88, 'Guinea', '224'),
(89, 'Guinea-Bissau', '245'),
(90, 'Guyana', '592'),
(91, 'Haiti', '509'),
(92, 'Honduras', '504'),
(93, 'Hong Kong', '852'),
(94, 'Hungary', '36'),
(95, 'Iceland', '354'),
(96, 'India', '91'),
(97, 'Indonesia', '62'),
(98, 'Iran', '98'),
(99, 'Iraq', '964'),
(100, 'Ireland', '353'),
(101, 'Israel', '972'),
(102, 'Italy', '39'),
(103, 'Jamaica', '1876'),
(104, 'Japan', '81'),
(105, 'Jordan', '962'),
(106, 'Kazakhstan', '77'),
(107, 'Kenya', '254'),
(108, 'Kiribati', '686'),
(109, 'Korea (North)', '850'),
(110, 'Korea (South)', '82'),
(111, 'Kuwait', '965'),
(112, 'Kyrgyzstan', '996'),
(113, 'Laos', '856'),
(114, 'Latvia', '371'),
(115, 'Lebanon', '961'),
(116, 'Lesotho', '266'),
(117, 'Liberia', '231'),
(118, 'Libya', '218'),
(119, 'Liechtenstein', '423'),
(120, 'Lithuania', '370'),
(121, 'Luxembourg ', '352'),
(122, 'Macau', '853'),
(123, 'Macedonia', '389'),
(124, 'Madagascar', '261'),
(125, 'Malawi', '265'),
(126, 'Malaysia', '60'),
(127, 'Maldives', '960'),
(128, 'Mali', '223'),
(129, 'Malta', '356'),
(130, 'Marshall Islands', '692'),
(131, 'Martinique', '596'),
(132, 'Mauritania', '222'),
(133, 'Mauritius', '230'),
(134, 'Mayotte', '52'),
(135, 'Mexico', '691'),
(136, 'Micronesia', '373'),
(137, 'Moldova', '377'),
(138, 'Monaco', '976'),
(139, 'Mongolia', '382'),
(140, 'Montserrat', '1664'),
(141, 'Morocco', '212'),
(142, 'Mozambique', '258'),
(143, 'Myanmar', '95'),
(144, 'Namibia', '264'),
(145, 'Nauru', '674'),
(146, 'Nepal', '977'),
(147, 'Netherlands', '31'),
(148, 'Netherlands Antilles', '599'),
(149, 'New Caledonia', '687'),
(150, 'New Zealand', '64'),
(151, 'Nicaragua', '505'),
(152, 'Niger', '227'),
(153, 'Nigeria', '234'),
(154, 'Niue', '683'),
(155, 'Norfolk Island', '6723'),
(156, 'Northern Marianas', '1670'),
(157, 'Norway', '47'),
(158, 'Oman', '968'),
(159, 'Pakistan', '92'),
(160, 'Palau', '680'),
(161, 'Palestinian Settlements', '970'),
(162, 'Panama', '507'),
(163, 'Papua New Guinea', '675'),
(164, 'Paraguay', '595'),
(165, 'Peru', '51'),
(166, 'Philippines', '63'),
(167, 'Poland', '48'),
(168, 'Portugal', '351'),
(169, 'Puerto Rico', '1787'),
(170, 'Qatar', '974'),
(171, 'R', '262'),
(172, 'Romania', '40'),
(173, 'Russia', '7'),
(174, 'Rwanda', '250'),
(175, 'Saint Helena', '290'),
(176, 'Saint Kitts and Nevis', '1869'),
(177, 'Saint Lucia', '1758'),
(178, 'Saint Pierre and Miquelon', '508'),
(179, 'Saint Vincent and Grenadines', '1784'),
(180, 'Samoa', '685'),
(181, 'San Marino', '378'),
(182, 'S', '239'),
(183, 'Saudi Arabia', '966'),
(184, 'Senegal', '221'),
(185, 'Serbia', '381'),
(186, 'Seychelles', '248'),
(187, 'Sierra Leone', '232'),
(188, 'Singapore', '65'),
(189, 'Slovakia', '421'),
(190, 'Slovenia', '386'),
(191, 'Solomon Islands', '677'),
(192, 'Somalia', '252'),
(193, 'South Africa', '27'),
(194, 'Spain', '34'),
(195, 'Sri Lanka', '94'),
(196, 'Sudan', '249'),
(197, 'Suriname', '597'),
(198, 'Swaziland', '268'),
(199, 'Sweden', '46'),
(200, 'Switzerland', '41'),
(201, 'Syria', '963'),
(202, 'Taiwan', '886'),
(203, 'Tajikistan', '992'),
(204, 'Tanzania', '255'),
(205, 'Thailand', '66'),
(206, 'Togo', '228'),
(207, 'Tokelau', '690'),
(208, 'Tonga', '676'),
(209, 'Trinidad and Tobago', '1868'),
(210, 'Tunisia', '216'),
(211, 'Turkey', '90'),
(212, 'Turkmenistan', '993'),
(213, 'Turks and Caicos Islands', '1649'),
(214, 'Tuvalu', '688'),
(215, 'Uganda', '256'),
(216, 'Ukraine', '380'),
(217, 'United Arab Emirates', '971'),
(218, 'United Kingdom', '44'),
(219, 'United States', '1'),
(220, 'Uruguay', '598'),
(221, 'US Virgin Islands', '1340'),
(222, 'Uzbekistan', '998'),
(223, 'Vanuatu', '678'),
(224, 'Venezuela', '58'),
(225, 'Vietnam', '84'),
(226, 'Virgin Islands', '1284'),
(227, 'Wake Island', '808'),
(228, 'Wallis and Futuna', '681'),
(229, 'Yemen', '967'),
(230, 'Zambia', '260'),
(231, 'Zimbabwe', '263');

CREATE TABLE  sm_currency_master
(
currency_key bigint auto_increment primary key,
currency_code varchar(50),
currency_description varchar(255)
);

INSERT INTO sm_currency_master (currency_code, currency_description) values
('USD', 'US Dollar'),
('AED', 'United Arab Emirates dirham'),
('AFN', 'Afghani'),
('ALL', 'Lek'),
('AMD', 'Armenian Dram'),
('ANG', 'Netherlands Antillian Guilder'),
('AOA', 'Kwanza'),
('ARS', 'Argentine Peso'),
('AUD', 'Australian Dollar'),
('AWG', 'Aruban Guilder'),
('AZN', 'Azerbaijanian Manat'),
('BAM', 'Convertible Marks'),
('BBD', 'Barbados Dollar'),
('BDT', 'Bangladeshi Taka'),
('BGN', 'Bulgarian Lev'),
('BHD', 'Bahraini Dinar'),
('BIF', 'Burundian Franc'),
('BMD', 'Bermudian Dollar (customarily known as Bermuda Dollar)'),
('BND', 'Brunei Dollar'),
('BOB', 'Boliviano'),
('BOV', 'Bolivian Mvdol (Funds code)'),
('BRL', 'Brazilian Real'),
('BSD', 'Bahamian Dollar'),
('BTN', 'Ngultrum'),
('BWP', 'Pula'),
('BYR', 'Belarussian Ruble'),
('BZD', 'Belize Dollar'),
('CAD', 'Canadian Dollar'),
('CDF', 'Franc Congolais'),
('CHE', 'WIR Euro (complementary currency)'),
('CHF', 'Swiss Franc'),
('CHW', 'WIR Franc (complementary currency)'),
('CLF', 'Unidades de formento (Funds code)'),
('CLP', 'Chilean Peso'),
('CNY', 'Yuan Renminbi'),
('COP', 'Colombian Peso'),
('COU', 'Unidad de Valor Real'),
('CRC', 'Costa Rican Colon'),
('CUP', 'Cuban Peso'),
('CVE', 'Cape Verde Escudo'),
('CYP', 'Cyprus Pound'),
('CZK', 'Czech Koruna'),
('DJF', 'Djibouti Franc'),
('DKK', 'Danish Krone'),
('DOP', 'Dominican Peso'),
('DZD', 'Algerian Dinar'),
('EEK', 'Kroon'),
('EGP', 'Egyptian Pound'),
('ERN', 'Nakfa'),
('ETB', 'Ethiopian Birr'),
('EUR', 'Euro'),
('FJD', 'Fiji Dollar'),
('FKP', 'Falkland Islands Pound'),
('GBP', 'Pound Sterling'),
('GEL', 'Lari'),
('GHS', 'Cedi'),
('GIP', 'Gibraltar pound'),
('GMD', 'Dalasi'),
('GNF', 'Guinea Franc'),
('GTQ', 'Quetzal'),
('GYD', 'Guyana Dollar'),
('HKD', 'Hong Kong Dollar'),
('HNL', 'Lempira'),
('HRK', 'Croatian Kuna'),
('HTG', 'Haiti Gourde'),
('HUF', 'Forint'),
('IDR', 'Rupiah'),
('ILS', 'New Israeli Shekel'),
('INR', 'Indian Rupee'),
('IQD', 'Iraqi Dinar'),
('IRR', 'Iranian Rial'),
('ISK', 'Iceland Krona'),
('JMD', 'Jamaican Dollar'),
('JOD', 'Jordanian Dinar'),
('JPY', 'Japanese yen'),
('KES', 'Kenyan Shilling'),
('KGS', 'Som'),
('KHR', 'Riel'),
('KMF', 'Comoro Franc'),
('KPW', 'North Korean Won'),
('KRW', 'South Korean Won'),
('KWD', 'Kuwaiti Dinar'),
('KYD', 'Cayman Islands Dollar'),
('KZT', 'Tenge'),
('LAK', 'Kip'),
('LBP', 'Lebanese Pound'),
('LKR', 'Sri Lanka Rupee'),
('LRD', 'Liberian Dollar'),
('LSL', 'Loti'),
('LTL', 'Lithuanian Litas'),
('LVL', 'Latvian Lats'),
('LYD', 'Libyan Dinar'),
('MAD', 'Moroccan Dirham'),
('MDL', 'Moldovan Leu'),
('MGA', 'Malagasy Ariary'),
('MKD', 'Denar'),
('MMK', 'Kyat'),
('MNT', 'Tugrik'),
('MOP', 'Pataca'),
('MRO', 'Ouguiya'),
('MTL', 'Maltese Lira'),
('MUR', 'Mauritius Rupee'),
('MVR', 'Rufiyaa'),
('MWK', 'Kwacha'),
('MXN', 'Mexican Peso'),
('MXV', 'Mexican Unidad de Inversion (UDI) (Funds code)'),
('MYR', 'Malaysian Ringgit'),
('MZN', 'Metical'),
('NAD', 'Namibian Dollar'),
('NGN', 'Naira'),
('NIO', 'Cordoba Oro'),
('NOK', 'Norwegian Krone'),
('NPR', 'Nepalese Rupee'),
('NZD', 'New Zealand Dollar'),
('OMR', 'Rial Omani'),
('PAB', 'Balboa'),
('PEN', 'Nuevo Sol'),
('PGK', 'Kina'),
('PHP', 'Philippine Peso'),
('PKR', 'Pakistan Rupee'),
('PLN', 'Zloty'),
('PYG', 'Guarani'),
('QAR', 'Qatari Rial'),
('RON', 'Romanian New Leu'),
('RSD', 'Serbian Dinar'),
('RUB', 'Russian Ruble'),
('RWF', 'Rwanda Franc'),
('SAR', 'Saudi Riyal'),
('SBD', 'Solomon Islands Dollar'),
('SCR', 'Seychelles Rupee'),
('SDG', 'Sudanese Pound'),
('SEK', 'Swedish Krona'),
('SGD', 'Singapore Dollar'),
('SHP', 'Saint Helena Pound'),
('SKK', 'Slovak Koruna'),
('SLL', 'Leone'),
('SOS', 'Somali Shilling'),
('SRD', 'Surinam Dollar'),
('STD', 'Dobra'),
('SYP', 'Syrian Pound'),
('SZL', 'Lilangeni'),
('THB', 'Baht'),
('TJS', 'Somoni'),
('TMM', 'Manat'),
('TND', 'Tunisian Dinar'),
('TOP', 'Pa''anga'),
('TRY', 'New Turkish Lira'),
('TTD', 'Trinidad and Tobago Dollar'),
('TWD', 'New Taiwan Dollar'),
('TZS', 'Tanzanian Shilling'),
('UAH', 'Hryvnia'),
('UGX', 'Uganda Shilling'),
('USN', ''),
('USS', ''),
('UYU', 'Peso Uruguayo'),
('UZS', 'Uzbekistan Som'),
('VEB', 'Venezuelan bol'),
('VND', 'Vietnamese ??ng'),
('VUV', 'Vatu'),
('WST', 'Samoan Tala'),
('XAF', 'CFA Franc BEAC'),
('XAG', 'Silver (one Troy ounce)'),
('XAU', 'Gold (one Troy ounce)'),
('XBA', 'European Composite Unit (EURCO) (Bonds market unit)'),
('XBB', 'European Monetary Unit (E.M.U.-6) (Bonds market unit)'),
('XBC', 'European Unit of Account 9 (E.U.A.-9) (Bonds market unit)'),
('XBD', 'European Unit of Account 17 (E.U.A.-17) (Bonds market unit)'),
('XCD', 'East Caribbean Dollar'),
('XDR', 'Special Drawing Rights'),
('XFO', 'Gold franc (special settlement currency)'),
('XFU', 'UIC franc (special settlement currency)'),
('XOF', 'CFA Franc BCEAO'),
('XPD', 'Palladium (one Troy ounce)'),
('XPF', 'CFP franc'),
('XPT', 'Platinum (one Troy ounce)'),
('XTS', 'Code reserved for testing purposes'),
('XXX', 'No currency'),
('YER', 'Yemeni Rial'),
('ZAR', 'South African Rand'),
('ZMK', 'Kwacha'),
('ZWD', 'Zimbabwe Dollar');

CREATE TABLE  sm_naic_master
(
naics_key bigint auto_increment primary key,
naics_code varchar(50),
naics_description varchar(255)
);

INSERT INTO sm_naic_master (naics_code, naics_description) values
('111', 'Crop Production '),
('112', 'Animal Production and Aquaculture '),
('113', 'Forestry and Logging '),
('114', 'Fishing, Hunting and Trapping'),
('115', 'Support Activities for Agriculture and Forestry'),
('211', 'Oil and Gas Extraction'),
('212', 'Mining (except Oil and Gas)'),
('213', 'Support Activities for Mining'),
('221', 'Utilities '),
('236', 'Construction of Buildings'),
('237', 'Heavy and Civil Engineering Construction'),
('238', 'Specialty Trade Contractors'),
('311', 'Food Manufacturing'),
('312', 'Beverage and Tobacco Product Manufacturing'),
('313', 'Textile Mills'),
('314', 'Textile Product Mills'),
('315', 'Apparel Manufacturing'),
('316', 'Leather and Allied Product Manufacturing'),
('321', 'Wood Product Manufacturing'),
('322', 'Paper Manufacturing'),
('323', 'Printing and Related Support Activities'),
('324', 'Petroleum and Coal Products Manufacturing'),
('325', 'Chemical Manufacturing'),
('326', 'Plastics and Rubber Products Manufacturing'),
('327', 'Nonmetallic Mineral Product Manufacturing'),
('331', 'Primary Metal Manufacturing'),
('332', 'Fabricated Metal Product Manufacturing'),
('333', 'Machinery Manufacturing'),
('334', 'Computer and Electronic Product Manufacturing'),
('335', 'Electrical Equipment, Appliance, and Component Manufacturing'),
('336', 'Transportation Equipment Manufacturing'),
('337', 'Furniture and Related Product Manufacturing'),
('339', 'Miscellaneous Manufacturing'),
('423', 'Merchant Wholesalers, Durable Goods '),
('424', 'Merchant Wholesalers, Nondurable Goods '),
('425', 'Wholesale Electronic Markets and Agents and Brokers '),
('441', 'Motor Vehicle and Parts Dealers '),
('442', 'Furniture and Home Furnishings Stores '),
('443', 'Electronics and Appliance Stores '),
('444', 'Building Material and Garden Equipment and Supplies Dealers '),
('445', 'Food and Beverage Stores '),
('446', 'Health and Personal Care Stores '),
('447', 'Gasoline Stations '),
('448', 'Clothing and Clothing Accessories Stores '),
('451', 'Sporting Goods, Hobby, Musical Instrument, and Book Stores '),
('452', 'General Merchandise Stores '),
('453', 'Miscellaneous Store Retailers '),
('454', 'Nonstore Retailers '),
('481', 'Air Transportation'),
('482', 'Rail Transportation'),
('483', 'Water Transportation'),
('484', 'Truck Transportation'),
('485', 'Transit and Ground Passenger Transportation'),
('486', 'Pipeline Transportation'),
('487', 'Scenic and Sightseeing Transportation'),
('488', 'Support Activities for Transportation'),
('491', 'Postal Service'),
('492', 'Couriers and Messengers'),
('493', 'Warehousing and Storage'),
('511', 'Publishing Industries (except Internet)'),
('512', 'Motion Picture and Sound Recording Industries'),
('515', 'Broadcasting (except Internet)'),
('517', 'Telecommunications'),
('518', 'Data Processing, Hosting, and Related Services'),
('519', 'Other Information Services'),
('521', 'Monetary Authorities-Central Bank'),
('522', 'Credit Intermediation and Related Activities'),
('523', 'Securities, Commodity Contracts, and Other Financial Investments and Related Activities'),
('524', 'Insurance Carriers and Related Activities'),
('525', 'Funds, Trusts, and Other Financial Vehicles '),
('531', 'Real Estate'),
('532', 'Rental and Leasing Services'),
('533', 'Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)'),
('541', 'Professional, Scientific, and Technical Services'),
('551', 'Management of Companies and Enterprises'),
('561', 'Administrative and Support Services'),
('562', 'Waste Management and Remediation Services'),
('611', 'Educational Services'),
('621', 'Ambulatory Health Care Services'),
('622', 'Hospitals'),
('623', 'Nursing and Residential Care Facilities'),
('624', 'Social Assistance'),
('711', 'Performing Arts, Spectator Sports, and Related Industries'),
('712', 'Museums, Historical Sites, and Similar Institutions'),
('713', 'Amusement, Gambling, and Recreation Industries'),
('721', 'Accommodation'),
('722', 'Food Services and Drinking Places'),
('811', 'Repair and Maintenance'),
('812', 'Personal and Laundry Services'),
('813', 'Religious, Grantmaking, Civic, Professional, and Similar Organizations'),
('814', 'Private Households'),
('921', 'Executive, Legislative, and Other General Government Support '),
('922', 'Justice, Public Order, and Safety Activities '),
('923', 'Administration of Human Resource Programs '),
('924', 'Administration of Environmental Quality Programs '),
('925', 'Administration of Housing Programs, Urban Planning, and Community Development '),
('926', 'Administration of Economic Programs '),
('927', 'Space Research and Technology '),
('928', 'National Security and International Affairs ');

CREATE TABLE  sm_pricing_master
(
pricing_key bigint auto_increment primary key,
plan varchar(255),
max_employee bigint(15),
max_transaction bigint(20),
disk_quota bigint(20)
);

INSERT INTO sm_pricing_master values
(1, 'Basic', 25, 250000, 250),
(2, 'Plus', 100, 1000000, 500),
(3, 'Premium', 0, 0, 750);

CREATE TABLE  sm_quantity_type_master
(
quantity_key bigint auto_increment primary key,
quantity_code varchar(50),
quantity_description varchar(255)
);

INSERT INTO sm_quantity_type_master (quantity_code, quantity_description) VALUES
('BAG', 'BAGS'),
('BAL', 'BALE'),
('BDL', 'BUNDLES'),
('BKL', 'BUCKLES'),
('BOU', 'BILLIONS OF UNITS'),
('BOX', 'BOX'),
('BTL', 'BOTTLES'),
('BUN', 'BUNCHES'),
('CAN', 'CANS'),
('CAS', 'CASE'),
('CBM', 'CUBIC METER'),
('CCM', 'CUBIC CENTIMETER'),
('CMS', 'CENTIMETER'),
('CTN', 'CARTONS'),
('DOZ', 'DOZEN'),
('DRM', 'DRUM'),
('FTS', 'FEET'),
('GGR', 'GREAT GROSS'),
('GMS', 'GRAMS'),
('GRS', 'GROSS'),
('GYD', 'GROSS YARDS'),
('KGA', 'KILOGRAM ACTIVITY'),
('KGS', 'KILOGRAMS'),
('KIT', 'KITS'),
('KLR', 'KILOLITER'),
('KME', 'KILOMETERS'),
('KWH', 'KILOWATTHOUR'),
('LBS', 'POUNDS'),
('LTR', 'LITERS'),
('MLT', 'MILLILITER'),
('MTR', 'METER'),
('MTS', 'METRIC TON'),
('NOS', 'NUMBER'),
('PAC', 'PACKS'),
('PCS', 'PIECES'),
('PRS', 'PAIRS'),
('QTL', 'QUINTAL'),
('RLS', 'ROLLS'),
('ROL', 'ROLLS'),
('SET', 'SETS'),
('SQF', 'SQUARE FEET'),
('SQM', 'SQUARE METER'),
('SQY', 'SQUARE YARDS');

CREATE TABLE  sm_state_master
(
state_key bigint auto_increment primary key,
country_key bigint,
state_name varchar(255)
);

INSERT INTO sm_state_master(state_key,state_name,country_key) VALUES
(1, 'Alabama', 219),
(2, 'Alaska', 219),
(3, 'Arizona', 219),
(4, 'Arkansas', 219),
(5, 'California', 219),
(6, 'Colorado', 219),
(7, 'Connecticut', 219),
(8, 'Delaware', 219),
(9, 'Florida', 219),
(10, 'Georgia', 219),
(11, 'Hawaii', 219),
(12, 'Idaho', 219),
(13, 'Illinois', 219),
(14, 'Indiana', 219),
(15, 'Iowa', 219),
(16, 'Kansas', 219),
(17, 'Kentucky', 219),
(18, 'Louisiana', 219),
(19, 'Maine', 219),
(20, 'Maryland', 219),
(21, 'Massachusetts', 219),
(22, 'Michigan', 219),
(23, 'Minnesota', 219),
(24, 'Mississippi', 219),
(25, 'Missouri', 219),
(26, 'Montana', 219),
(27, 'Nebraska', 219),
(28, 'Nevada', 219),
(29, 'New Hampshire', 219),
(30, 'New Jersey', 219),
(31, 'New Mexico', 219),
(32, 'New York', 219),
(33, 'North Carolina', 219),
(34, 'North Dakota', 219),
(35, 'Ohio', 219),
(36, 'Oklahoma', 219),
(37, 'Oregon', 219),
(38, 'Pennsylvania', 219),
(39, 'Rhode Island', 219),
(40, 'South Carolina', 219),
(41, 'South Dakota', 219),
(42, 'Tennessee', 219),
(43, 'Texas', 219),
(44, 'Utah', 219),
(45, 'Vermont', 219),
(46, 'Virginia', 219),
(47, 'Washington', 219),
(48, 'West Virginia', 219),
(49, 'Wisconsin', 219),
(50, 'Wyoming', 219),
(51, 'Andaman and Nikobar', 96),
(52, 'Andhra Pradesh', 96),
(53, 'Arunachal Pradesh', 96),
(54, 'Assam', 96),
(55, 'Bihar', 96),
(56, 'Chandigarh', 96),
(57, 'Chhattisgarh', 96),
(58, 'Dadra and Nagar Haveli', 96),
(59, 'Daman and Diu', 96),
(60, 'New Delhi', 96),
(61, 'Goa', 96),
(62, 'Gurjrat', 96),
(63, 'Haryana', 96),
(64, 'Himachal Pradesh', 96),
(65, 'Jammu and Kashmir', 96),
(66, 'Jharkhand', 96),
(67, 'Karnataka', 96),
(68, 'Kerala', 96),
(69, 'Lakshadeep', 96),
(70, 'Madhya Pradesh', 96),
(71, 'Maharashtra', 96),
(72, 'Manipur', 96),
(73, 'Meghalaya', 96),
(74, 'Mijoram', 96),
(75, 'Nagaland', 96),
(76, 'Orissa', 96),
(77, 'Puducherry', 96),
(78, 'Punjab', 96),
(79, 'Rajasthan', 96),
(80, 'Sikkim', 96),
(81, 'Tamilnadu', 96),
(82, 'Telangana', 96),
(83, 'Tripura', 96),
(84, 'Uttar Pradesh', 96),
(85, 'Uttrakhanad', 96),
(86, 'West Bengal', 96);

create table company_master
(
company_key bigint auto_increment primary key,
company_type varchar(50),
company_logo_path varchar(50),
company_name varchar(200),
company_id varchar(100),
segment_division varchar(100),
business_unit varchar(100),
pricing_key bigint,
amount_genrated decimal(20,2),
amount_platform decimal(20,2),
amount_paid decimal(20,2),
created_on datetime
);

create table company_mailing_address_master
(
mailing_key bigint auto_increment primary key,
company_key varchar(100),
branch varchar(100),
country varchar(100),
address varchar(200),
city varchar(200),
state varchar(200),
zipcode varchar(200),
address_type varchar(300), -- by admin/by drop shipper
added_by_user_key bigint,
created_on datetime
);

create table company_business_category_master
(
cbc_key bigint auto_increment primary key,
company_key bigint,
business_category_key bigint
);

create table user_master
(
user_key bigint auto_increment primary key,
company_key varchar(100),
user_type varchar(20), -- intranet / regular / admin
first_name varchar(200),
last_name varchar(200),
user_pic_path varchar(50),
title varchar(50),
department varchar(100),
manager_supervisor varchar(100),
phone varchar(50),
cell varchar(50),
fax varchar(50),
email varchar(100),
pass_word varchar(100),
created_on datetime,
account_status varchar(20), -- Pendind/Activated/Blocked
account_timestamp varchar(20),
account_activated_on datetime
);

create table user_mailing_address_master
(
mailing_key bigint auto_increment primary key,
user_key varchar(100),
country varchar(100),
address varchar(200),
city varchar(200),
state varchar(200),
zipcode varchar(200),
created_on datetime
);

create table account_policy_master
(
account_policy_key bigint auto_increment primary key,
company_key bigint,
user_key bigint,
enforce_password_history int,
max_password_age int,
min_password_age int,
min_password_length int,
send_email_before_password_expire int,
send_daily_reminder_after_date varchar(3), -- yes / no
password_complexity varchar(3),
upper_lower_case_letter varchar(3),
numerical_characters varchar(3),
non_alphanummeric_characters varchar(3),
account_lock_after_attempts int,
lockout_duration int,
reset_lockout_counter_after int,
account_unlocked_by_admin varchar(3),
session_end_time int
);

create table user_setting_master
(
user_key bigint primary key,
user_status varchar(25), -- Connected (Online) / Not Connected (Offline)

new_email varchar(50), -- new email address store
use_registered_email varchar(3), -- yes / no of first checkbox
new_mobile  varchar(10), -- new phone number store 10 digits
workinghours_notify varchar(3), -- yes / no of second checkbox
nonworkinghours_notify varchar(3), -- email / sms / both of first drop down
workinghours_mode varchar(10), -- yes / no of second checkbox
nonworkinghours_mode varchar(10), -- email / sms / both of third drop down
stop_notify varchar(3), -- yes / no of forth check box
stoptime_from date, -- from date in yyyy-mm-dd format by manual
stoptime_to date, -- to date in yyyy-mm-dd format by manual

working_days varchar(7),
sun_from time,
sun_to time,
mon_from time,
mon_to time,
tue_from time,
tue_to time,
wed_from time,
wed_to time,
thu_from time,
thu_to time,
fri_from time,
fri_to time,
sat_from time,
sat_to time
);

create table invite_master
(
invite_key bigint auto_increment primary key,
user_key bigint,
email varchar(300),
invited_on datetime
);

create table company_website_master
(
company_website_key bigint auto_increment primary key,
company_key bigint,
url_name varchar(300),
website_url varchar(300)
);

create table company_advertisement_master
(
advertisement_key bigint auto_increment primary key,
company_key bigint,
user_key bigint,
hover_text varchar(300),
link_page varchar(300),
image_path varchar(500) 
);

create table group_master
(
group_key bigint auto_increment primary key,
company_key bigint,
group_name varchar(255)
);

create table group_user_master
(
group_user_key bigint auto_increment primary key,
company_key bigint,
group_key bigint,
user_key bigint
);

create table department_master
(
department_key bigint auto_increment primary key,
company_key bigint,
department_name varchar(255)
);

create table department_user_master
(
department_user_key bigint auto_increment primary key,
company_key bigint,
department_key bigint,
user_key bigint
);

create table department_group_master
(
department_group_key bigint auto_increment primary key,
company_key bigint,
department_key bigint,
group_key bigint
);

create table folder_master
(
folder_key varchar(30) primary key,
company_key bigint,
folder_name varchar(255)
);

create table department_folder_master
(
department_folder_key bigint auto_increment primary key,
company_key bigint,
department_key bigint,
folder_key varchar(30)
);

create table watchlist_master
(
watchlist_key bigint auto_increment primary key,
user_key bigint,
watch_list_name varchar(50)
);

create table watchlist_member_master
(
watchlist_member_key bigint auto_increment primary key,
watchlist_key bigint,
user_key bigint
);

create table feed_master
(
feed_key bigint auto_increment primary key,
feed_type varchar(25), -- user feed / department feed / internal feed
company_key bigint,
user_key bigint,
department_key bigint,
is_feed_company_wide varchar(3),
feed_title varchar(100),
feed_description varchar(200),
file_path varchar(200),
posted_on datetime
);

create table feed_like_master
(
feed_like_key bigint auto_increment primary key,
feed_key bigint,
user_key bigint,
liked_on datetime
);

create table feed_comment_master
(
feed_comment_key bigint auto_increment primary key,
feed_key bigint,
user_key bigint,
comment varchar(200),
commented_on datetime
);

create table message_master
(
message_key bigint auto_increment primary key,
user_key_from bigint,
user_key_to bigint,
message varchar(1000),
delete_from bigint,
delete_to bigint,
message_on datetime,
mstatus varchar(6) default 'unread'
);

create table notification_master
(
notification_key bigint auto_increment primary key,
user_key_from bigint,
user_key_to bigint,
company_key_from bigint,
company_key_to bigint,
notification_type varchar(100), -- Vendor Registartion / VR Acceptance / VR Rejection / 
notification_type_id bigint,
notification_message varchar(500),
status varchar(6), -- read / unread
notification_on datetime
);

create table rating_master
(
rating_key bigint auto_increment primary key,
user_key_from bigint,
user_key_to bigint,
company_key_from bigint,
company_key_to bigint,
rating_title varchar(100),
rating_comment varchar(200),
rating bigint,
rating_on datetime
);

create table connection_master
(
connection_key bigint auto_increment primary key,
user_key_from bigint,
user_key_to bigint,
company_key_from bigint,
company_key_to bigint,
status varchar(50),
sent_on datetime
);

create table vendor_registration_master
(
vr_key bigint auto_increment primary key,

company_key_from bigint, -- sender company key
company_key_to bigint, -- receiver company key
user_key_from bigint, -- sender user key
user_key_to bigint, -- receiver company key

request_sender_type varchar(10), -- Buyer / Supplier -- will be in hidden field and submitted to servlet
company_type varchar(100), -- values must be equal to labels and these values of radio buttons
business_contact_name varchar(50), -- name of user
business_tax_id varchar(50), -- submitted by user
naics_code bigint, -- fetch on form from database and naics_code will be submitted via option value here not naics_key

w9tax_form_status varchar(3), -- yes for checked / no for unchecked
w9tax_form_path varchar(200), -- where we are storing w9forms

business_size varchar(5), -- large / small
business_classification varchar(14), -- 11 bits for 11 check boxes (0 for unchecked and 1 for checked)
additional_details varchar(1000), -- Message typed in textarea by user

request_status varchar(20), -- Form Sent / Accepted / Rejected
sent_on datetime, -- sender sysdate() when sent
accepted_on datetime -- sender sysdate() when sent and received sender sysdate() when accept / reject
);

create table terms_conditions_master
(
tc_key bigint auto_increment primary key,
company_key bigint,
transaction_type varchar(3), -- RQF / QTE / PO / INV
text_message varchar(1000),
created_on datetime
);

create table product_master
(
item_key bigint auto_increment primary key,
company_key bigint,
item_name varchar(100),
item_description varchar(1000),
part_no varchar(50),
sku varchar(50),
barcode varchar(50),
quantity decimal(11,2),
quantity_key bigint,
price decimal(12,2),
currency_key bigint,
tax decimal(11,2),
product_display varchar(3),
pics_count varchar(1000)
);

create table trans_rfq_master
(
trans_rfq_key bigint auto_increment primary key,
rfq_no varchar(20),
company_key_from bigint,
company_key_to bigint,
user_key_from bigint,
user_key_to bigint,
trans_status varchar(50),
trans_action varchar(50), -- accepted / rejected
quote_ref varchar(20),
is_outside varchar(3), -- yes / no
is_outside_address varchar(200),
recurring varchar(20), -- W / M / Q / H / Y 
is_quote_created varchar(3), -- yes / no
created_on datetime,
action_on datetime
);

create table trans_rfq_item_master
(
trans_rqf_item_key bigint auto_increment primary key,
trans_rqf_key bigint,
rfq_no varchar(50),
item_key bigint,
part_no varchar(50),
barcode varchar(50),
quantity decimal(12,2),
quantity_unit_key bigint,
ship_date datetime
);

create table transaction_master
(
trans_key bigint auto_increment primary key,
company_key_from bigint,
company_key_to bigint,
user_key_from bigint,
user_key_to bigint,
transaction_type varchar(20), -- quote / po / invoice

q_trans_rqf_key bigint,
q_quote_no varchar(20),
q_trans_status varchar(50),
q_trans_action varchar(50), -- accepted / rejected
q_quote_ref varchar(20),
q_is_outside varchar(3), -- yes / no
q_is_outside_address varchar(200),
q_recurring varchar(20), -- W / M / Q / H / Y
q_total_amount double(20,2),
q_tax_percentage double(20,2),
q_total_billing_amount double(20,2),
q_is_po_created varchar(3), -- yes / no
q_created_on datetime,
q_action_on datetime,

po_trans_rqf_key bigint,
po_no varchar(20),
po_trans_status varchar(50),
po_trans_action varchar(50), -- accepted / rejected
po_quote_ref varchar(20),
po_is_outside varchar(3), -- yes / no
po_is_outside_address varchar(200),
po_recurring varchar(20), -- W / M / Q / H / Y
po_total_amount double(20,2),
po_tax_percentage double(20,2),
po_total_billing_amount double(20,2),
po_is_inv_created varchar(3), -- yes / no
po_created_on datetime,
po_action_on datetime,

inv_trans_rqf_key bigint,
inv_quote_no varchar(20),
inv_trans_status varchar(50),
inv_trans_action varchar(50), -- accepted / rejected
inv_quote_ref varchar(20),
inv_is_outside varchar(3), -- yes / no
inv_is_outside_address varchar(200),
inv_recurring varchar(20), -- W / M / Q / H / Y 
inv_total_amount double(20,2),
inv_tax_percentage double(20,2),
inv_total_billing_amount double(20,2),
inv_is_po_created varchar(3), -- yes / no
inv_created_on datetime,
inv_action_on datetime,

freight_handling double(10,2),
discount double(20,2),
invoice_billing_period varchar(255),
invoice_due_date datetime,
invoice_no varchar(255),
freight_carrier varchar(1024),
bill_of_landing varchar(50),
freight_weight double(10,2),
freight_weight_unit bigint, -- unit from quantity_unit_key
shipped_date date,
is_nonpo_invoice varchar(3),
po_num varchar(255),
is_diff_address varchar(3),
diff_address varchar(500),
is_inv_paid varchar(3),
inv_paid_on_dated date
);

create table transaction_item_master
(
trans_item_key bigint auto_increment primary key,
trans_key bigint,

q_quote_no varchar(20),
q_item_key bigint,
q_part_no varchar(50),
q_barcode varchar(50),
q_quantity double(10,2),
q_quantity_type_key bigint,
q_ship_date datetime,
q_price double(20,2),
q_currency_key bigint,
q_multiplier int,

po_po_no varchar(20),
po_item_key bigint,
po_part_no varchar(50),
po_barcode varchar(50),
po_quantity double(10,2),
po_quantity_type_key bigint,
po_ship_date datetime,
po_price double(20,2),
po_currency_key bigint,
po_multiplier int,

inv_inv_no varchar(20),
inv_item_key bigint,
inv_part_no varchar(50),
inv_barcode varchar(50),
inv_quantity_oredered double(10,2),
inv_quantity_oredered_type_key bigint,
inv_quantity_shipped double(10,2),
inv_quantity_shipped_type_key bigint,
inv_ship_date datetime,
inv_price double(20,2),
inv_currency_key bigint,
inv_multiplier int
);

create table inquire_master
(
inquiry_key bigint auto_increment primary key,
inquire_type varchar(3), -- VR / RFQ / QTE / PO / INV
vr_key bigint,
rfq_key bigint,
transaction_key bigint,
inquire_from bigint,
inquire_to bigint,
inquire_details varchar(1000),
sent_on datetime
);

create table group_perm_master
(
group_perm_key bigint auto_increment primary key,
add_user varchar(3), -- yes/no
delete_user varchar(3), -- yes/no
add_vendor varchar(3), -- yes/no
delete_vendor varchar(3), -- yes/no
access_user_mngmt varchar(3), -- yes/no
post_announcements varchar(3), -- yes/no
rate_vendor varchar(3), -- yes/no
add_group varchar(3), -- yes/no
delete_group varchar(3), -- yes/no
upload_docs varchar(3), -- yes/no
delete_docs varchar(3), -- yes/no
delete_announcements varchar(3), -- yes/no
apply_theme_borders varchar(3), -- yes/no
manage_folders varchar(3) -- yes/no
);

create table department_perm_master
(
department_perm_key bigint auto_increment primary key,
add_folder varchar(3), -- yes/no
delete_folder varchar(3), -- yes/no
add_file varchar(3), -- yes/no
delete_file varchar(3), -- yes/no
post_announcements varchar(3), -- yes/no
manage_folders varchar(3) -- yes/no
);

create table wk_user
(
wk_user_id int auto_increment primary key,
email varchar(100),
password varchar(50),
status int(2)
);

insert into wk_user values(1,'info@supplymedium.com','SupplyMedium!1',2);

