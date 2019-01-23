/*
campos:
id: ID num�rico seg�n la ISO 3166-1 y la Divisi�n Estad�stica de las Naciones Unidas
iso2: c�digo de dos letras seg�n la ISO 3166-1
iso3: c�digo de tres letras seg�n la ISO 3166-1
prefijo: prefijo telef�nico seg�n la recomendaci�n E.164
nombre: nombre completo en espa�ol
continente: nombre del continente en espa�ol
subcontinente: nombre del subcontinente en espa�ol (para diferenciar Am�rica del Sur/Central/Norte/Caribe)
iso_moneda: c�digo de tres letras de su moneda seg�n la ISO 4217
nombre_moneda: nombre de la moneda en espa�ol
*/

-- phpMyAdmin SQL Dump
-- version 3.4.4
-- http://www.phpmyadmin.net
--
-- Servidor: 192.168.1.167
-- Tiempo de generaci�n: 23-05-2012 a las 11:13:15
-- Versi�n del servidor: 5.0.51
-- Versi�n de PHP: 5.2.6-1+lenny13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!4 0101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE TABLE paises (
  id smallint(3) unsigned zerofill NOT NULL,
  iso2 char(2) COLLATE utf8_unicode_ci NOT NULL,
  iso3 char(3) COLLATE utf8_unicode_ci NOT NULL,
  prefijo smallint(5) unsigned NOT NULL,
  nombre varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  continente varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  subcontinente varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  iso_moneda varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  nombre_moneda varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY iso2 (iso2),
  UNIQUE KEY iso3 (iso3)
);

INSERT INTO paises (id, iso2, iso3, prefijo, nombre, continente, subcontinente, iso_moneda, nombre_moneda) VALUES
(004, 'AF', 'AFG', 93, 'Afganist�n', 'Asia', NULL, 'AFN', 'Afgani afgano'),
(008, 'AL', 'ALB', 355, 'Albania', 'Europa', NULL, 'ALL', 'Lek alban�s'),
(010, 'AQ', 'ATA', 672, 'Ant rtida', 'Ant�rtida', NULL, NULL, NULL),
(012, 'DZ', 'DZA', 213, 'Argelia', '�frica', NULL, 'DZD', 'Dinar algerino'),
(016, 'AS', 'ASM', 1684, 'Samoa Americana', 'Ocean�a', NULL, NULL, NULL),
(020, 'AD', 'AND', 376, 'Andorra', 'Europa', NULL, 'EUR', 'Euro'),
(024, 'AO', 'AGO', 244, 'Angola', '�frica', NULL, 'AOA', 'Kwanza angole�o'),
(028, 'AG', 'ATG', 1268, 'Antigua y Barbuda', 'Am�rica', 'El Caribe', NULL, NULL),
(031, 'AZ', 'AZE', 994, 'Azerbaiy�n', 'Asia', NULL, 'AZM', 'Manat azerbaiyano'),
(032, 'AR', 'ARG', 54, 'Argentina', 'Am�rica', 'Am�rica del Sur', 'ARS', 'Peso argentino'),
(036, 'AU', 'AUS', 61, 'Australia', 'Ocean�a', NULL, 'AUD', 'D�lar australiano'),
(040, 'AT', 'AUT', 43, 'Austria', 'Europa', NULL, 'EUR', 'Euro'),
(044, 'BS', 'BHS', 1242, 'Bahamas', 'Am�rica', 'El Caribe', 'BSD', 'D�lar bahame�o'),
(048, 'BH', 'BHR', 973, 'Bahr�in', 'Asia', NULL, 'BHD', 'Dinar bahrein�'),
(050, 'BD', 'BGD', 880, 'Bangladesh', 'Asia', NULL, 'BDT', 'Taka de Bangladesh'),
(051  'AM', 'ARM', 374, 'Armenia', 'Asia', NULL, 'AMD', 'Dram armenio'),
(052, 'BB', 'BRB', 1246, 'Barbados', 'Am�rica', 'El Caribe', 'BBD', 'D�lar de Barbados'),
(056, 'BE', 'BEL', 32, 'B�lgica', 'Europa', NULL, 'EUR', 'Euro'),
(060, 'BM', 'BMU', 1441, 'Bermudas', 'Am�rica', 'El Caribe', 'BMD', 'D�lar de Bermuda'),
(064, 'BT', 'BTN', 975, 'Bhut�n', 'Asia', NULL, 'BTN', 'Ngultrum de But�n'),
(068, 'BO', 'BOL', 591, 'Bolivia', 'Am�rica', 'Am�rica del Sur', 'BOB', 'Boliviano'),
(070, 'BA', 'BIH', 387, 'Bosnia y Herzegovina', 'Europa', NULL, 'BAM', 'Marco convertible de Bosnia-Herzegovina'),
(072, 'BW', 'BWA', 267, 'Botsuana', '�frica', NULL, 'BWP', 'Pula de Botsuana'),
(074, 'BV', 'BVT', 0, 'Isla Bouvet', NULL, NULL, NULL, NULL),
(076, 'BR', 'BRA', 55, 'Brasil', 'Am�rica', 'Am�rica del Sur', 'BRL', 'Real brasile�o'),
(084, 'BZ', 'BLZ', 501, 'Belice', 'Am�rica', 'Am�rica Central', 'BZD', 'D�lar de Belice'),
(086, 'IO', 'IOT', 0, 'Territorio Brit�nico del Oc�ano �ndico', NULL, NULL, NULL, NULL),
(090, 'SB', 'SLB', 677, 'Islas Salom�n', 'Ocean�a', NULL, 'SBD', 'D�lar de las Islas Salom�n'),
(092, 'VG', 'VGB', 1284, 'Islas V�rgenes Brit�nicas', 'Am�rica', 'El Caribe', NULL, NULL),
(096, 'BN', 'BRN', 673, 'Brun�i', 'Asia', NULL, 'BND', 'D�lar de Brun�i'),
(100, 'BG', 'BGR', 359, 'Bulgaria', 'Europa', NULL, 'BGN', 'Lev b�lgaro'),
(104, 'MM', 'MMR', 95, 'Myanmar', 'Asia', NULL, 'MMK', 'Kyat birmano'),
(108, 'BI', 'BDI', 257, 'Burundi', '�frica', NULL, 'BIF', 'Franco burund�s'),
(112, 'BY', 'BLR', 375, 'Bielorrusia', 'Europa', NULL, 'BYR', 'Rublo bielorruso'),
(116, 'KH', 'KHM', 855, 'Camboya', 'Asia', NULL, 'KHR', 'Riel camboyano'),
(120, 'CM', 'CMR', 237, 'Camer�n', '�frica', NULL, NULL, NULL),
(124, 'CA', 'CAN', 1, 'Canad�', 'Am�rica', 'Am�rica del Norte', 'CAD', 'D�lar canadiense'),
(132, 'CV', 'CPV', 238, 'Cabo Verde', '�frica', NULL, 'CVE', 'Escudo caboverdiano'),
(136, 'KY', 'CYM', 1345, 'Islas Caim�n', 'Am�rica', 'El Caribe', 'KYD', 'D�lar caimano ( e Islas Caim�n)'),
(140, 'CF', 'CAF', 236, 'Rep�blica Centroafricana', '�frica', NULL, NULL, NULL),
(144, 'LK', 'LKA', 94, 'Sri Lanka', 'Asia', NULL, 'LKR', 'Rupia de Sri Lanka'),
(148, 'TD', 'TCD', 235, 'Chad', '�frica', NULL, NULL, NULL),
(152, 'CL', 'CHL', 56, 'Chile', 'Am�rica', 'Am�rica del Sur', 'CLP', 'Peso chileno'),
(156, 'CN', 'CHN', 86, 'China', 'Asia', NULL, 'CNY', 'Yuan Renminbi de China'),
(158, 'TW', 'TWN', 886, 'Taiw�n', 'Asia', NULL, 'TWD', 'D�lar taiwan�s'),
(162, 'CX', 'CXR', 61, 'Isla de Navidad', 'Ocean�a', NULL, NULL, NULL),
(166, 'CC', 'CCK', 61, 'Islas Cocos', '�cean�a', NULL, NULL, NULL),
(170, 'CO', 'COL', 57, 'Colombia', 'Am�rica', 'Am�rica del Sur', 'COP', 'Peso colombiano'),
(174, 'KM', 'COM', 269, 'Comoras', '�frica', NULL, 'KMF', 'Franco comoriano (de Comoras)'),
(175, 'YT', 'MYT', 262, 'Mayotte', '�frica', NULL, NULL, NULL),
(178, 'CG', 'COG', 242, 'Congo', '�frica', NULL, NULL, NULL),
(180, 'CD', 'COD', 243, 'Rep�blica Democr�tica del Cong ', '�frica', NULL, 'CDF', 'Franco congole�o'),
(184, 'CK', 'COK', 682, 'Islas Cook', 'Ocean�a', NULL, NULL, NULL),
(188, 'CR', 'CRI', 506, 'Costa Rica', 'Am�rica', 'Am�rica Central', 'CRC', 'Col�n costarricense'),
(191, 'HR', 'HRV', 385, 'Croacia', 'Europa', NULL, 'HRK', 'Kuna croata'),
(192, 'CU', 'CUB', 53, 'Cuba', 'Am�rica', 'El Caribe', 'CUP', 'Peso cubano'),
(196, 'CY', 'CYP', 357, 'Chipre', 'Europa', NULL, 'CYP', 'Libra chipriota'),
(203, 'CZ', 'CZE', 420, 'Rep�blica Checa', 'Europa', NULL, 'CZK', 'Koruna checa'),
(204, 'BJ', 'BEN', 229, 'Ben�n', '�frica', NULL, NULL, NULL),
(208, 'DK', 'DNK', 45, 'Dinamarca', 'Europa', NULL, 'DKK', 'Corona danesa'),
(212, 'DM', 'DMA', 1767, 'Dominica', 'Am�rica', 'El Caribe', NULL, NULL),
(214, 'DO', 'DOM', 1809, 'Rep�blica Dominicana', 'Am�rica', 'El Caribe', 'DOP', 'Peso dominicano'),
(218, 'EC', 'ECU', 593, 'Ecuador', 'Am�rica', 'Am�rica del Sur', NULL, NULL),
(222, 'SV', 'SLV', 503, 'El Salvador', 'Am�rica', 'Am�rica Central', ' VC', 'Col�n salvadore�o'),
(226, 'GQ', 'GNQ', 240, 'Guinea Ecuatorial', '�frica', NULL, NULL, NULL),
(231, 'ET', 'ETH', 251, 'Etiop�a', '�frica', NULL, 'ETB', 'Birr et�ope'),
(232, 'ER', 'ERI', 291, 'Eritrea', '�frica', NULL, 'ERN', 'Nakfa eritreo'),
(233, 'EE', 'EST', 372, 'Estonia', 'Europa', NULL, 'EEK', 'Corona estonia'),
(234, 'FO', 'FRO', 298, 'Islas Feroe', 'Europa', NULL, NULL, NULL),
(238, 'FK', 'FLK', 500, 'Islas Malvinas', 'Am�rica', 'Am�rica del Sur', 'FKP', 'Libra malvinense'),
(239, 'GS', 'SGS', 0, 'Islas Georgias del Sur y Sandwich del Sur', 'Am�rica', 'Am�rica del Sur', NULL, NULL),
(242, 'FJ', 'FJI', 679, 'Fiyi', 'Ocean�a', NULL, 'FJD', 'D�lar fijiano'),
(246, 'FI', 'FIN', 358, 'Finlandia', 'Europa', NULL, 'EUR', 'Euro'),
(248, 'AX', 'ALA', 0, 'Islas Gland', 'Europa', NULL, NULL, NULL),
(250, 'FR', 'FRA', 33, 'Francia', 'Europa', NULL, 'EUR', 'Euro'),
(254, 'GF', 'GUF', 0, 'Guayana Francesa', 'Am�rica', 'Am�rica del Sur', NULL, NULL),
(258, 'PF', 'PYF', 6 9, 'Polinesia Francesa', 'Ocean�a', NULL, NULL, NULL),
(260, 'TF', 'ATF', 0, 'Territorios Australes Franceses', NULL, NULL, NULL, NULL),
(262, 'DJ', 'DJI', 253, 'Yibuti', '�frica', NULL, 'DJF', 'Franco yibutiano'),
(266, 'GA', 'GAB', 241, 'Gab�n', '�frica', NULL, NULL, NULL),
(268, 'GE', 'GEO', 995, 'Georgia', 'Europa', NULL, 'GEL', 'Lari georgiano'),
(270, 'GM', 'GMB', 220, 'Gambia', '�frica', NULL, 'GMD', 'Dalasi gambiano'),
(275, 'PS', 'PSE', 0, 'Palestina', 'Asia', NULL, NULL, NULL),
(276, 'DE', 'DEU', 49, 'Alemania', 'Europa', NULL, 'EUR', 'Euro'),
(288, 'GH', 'GHA', 233, 'Ghana', '�frica', NULL, 'GHC', 'Cedi ghan�s'),
(292, 'GI', 'GIB', 350, 'Gibraltar', 'Europa', NULL, 'GIP', 'Libra de Gibraltar'),
(296, 'KI', 'KIR', 686, 'Kiribati', 'Ocean�a', NULL, NULL, NULL),
(300, 'GR', 'GRC', 30, 'Grecia', 'Europa', NULL, 'EUR', 'Euro'),
(304, 'GL', 'GRL', 299, 'Groenlandia', 'Am�rica', 'Am�rica del Norte', NULL, NULL),
(308, 'GD', 'GRD', 1473, 'Granada', 'Am�rica', 'El Carib ', NULL, NULL),
(312, 'GP', 'GLP', 0, 'Guadalupe', 'Am�rica', 'El Caribe', NULL, NULL),
(316, 'GU', 'GUM', 1671, 'Guam', 'Ocean�a', NULL, NULL, NULL),
(320, 'GT', 'GTM', 502, 'Guatemala', 'Am�rica', 'Am�rica Central', 'GTQ', 'Quetzal guatemalteco'),
(324, 'GN', 'GIN', 224, 'Guinea', '�frica', NULL, 'GNF', 'Franco guineano'),
(328, 'GY', 'GUY', 592, 'Guyana', 'Am�rica', 'Am�rica del Sur', 'GYD', 'D�lar guyan�s'),
(332, 'HT', 'HTI', 509, 'Hait�', 'Am�rica', 'El Caribe', 'HTG', 'Gourde haitiano'),
(334, 'HM', 'HMD', 0, 'Islas Heard y McDonald', 'Ocean�a', NULL, NULL, NULL),
(336, 'VA', 'VAT', 39, 'Ciudad del Vaticano', 'Europa', NULL, NULL, NULL),
(340, 'HN', 'HND', 504, 'Honduras', 'Am�rica', 'Am�rica Central', 'HNL', 'Lempira hondure�o'),
(344, 'HK', 'HKG', 852, 'Hong Kong', 'Asia', NULL, 'HKD', 'D�lar de Hong Kong'),
(348, 'HU', 'HUN', 36, 'Hungr�a', 'Europa', NULL, 'HUF', 'Forint h�ngaro'),
(352, 'IS', 'ISL', 354, 'Islandia', 'Europa', NULL, 'ISK', 'Kr�na islandesa'),
(3 6, 'IN', 'IND', 91, 'India', 'Asia', NULL, 'INR', 'Rupia india'),
(360, 'ID', 'IDN', 62, 'Indonesia', 'Asia', NULL, 'IDR', 'Rupiah indonesia'),
(364, 'IR', 'IRN', 98, 'Ir�n', 'Asia', NULL, 'IRR', 'Rial iran�'),
(368, 'IQ', 'IRQ', 964, 'Iraq', 'Asia', NULL, 'IQD', 'Dinar iraqu�'),
(372, 'IE', 'IRL', 353, 'Irlanda', 'Europa', NULL, 'EUR', 'Euro'),
(376, 'IL', 'ISR', 972, 'Israel', 'Asia', NULL, 'ILS', 'Nuevo sh�quel israel�'),
(380, 'IT', 'ITA', 39, 'Italia', 'Europa', NULL, 'EUR', 'Euro'),
(384, 'CI', 'CIV', 225, 'Costa de Marfil', '�frica', NULL, NULL, NULL),
(388, 'JM', 'JAM', 1876, 'Jamaica', 'Am�rica', 'El Caribe', 'JMD', 'D�lar jamaicano'),
(392, 'JP', 'JPN', 81, 'Jap�n', 'Asia', NULL, 'JPY', 'Yen japon�s'),
(398, 'KZ', 'KAZ', 7, 'Kazajst�n', 'Asia', NULL, 'KZT', 'Tenge kazajo'),
(400, 'JO', 'JOR', 962, 'Jordania', 'Asia', NULL, 'JOD', 'Dinar jordano'),
(404, 'KE', 'KEN', 254, 'Kenia', '�frica', NULL, 'KES', 'Chel�n keniata'),
(408, 'KP', 'PRK', 850, 'Corea del Norte'  'Asia', NULL, 'KPW', 'Won norcoreano'),
(410, 'KR', 'KOR', 82, 'Corea del Sur', 'Asia', NULL, 'KRW', 'Won surcoreano'),
(414, 'KW', 'KWT', 965, 'Kuwait', 'Asia', NULL, 'KWD', 'Dinar kuwait�'),
(417, 'KG', 'KGZ', 996, 'Kirguist�n', 'Asia', NULL, 'KGS', 'Som kirgu�s (de Kirguist�n)'),
(418, 'LA', 'LAO', 856, 'Laos', 'Asia', NULL, 'LAK', 'Kip lao'),
(422, 'LB', 'LBN', 961, 'L�bano', 'Asia', NULL, 'LBP', 'Libra libanesa'),
(426, 'LS', 'LSO', 266, 'Lesotho', '�frica', NULL, 'LSL', 'Loti lesotense'),
(428, 'LV', 'LVA', 371, 'Letonia', 'Europa', NULL, 'LVL', 'Lat let�n'),
(430, 'LR', 'LBR', 231, 'Liberia', '�frica', NULL, 'LRD', 'D�lar liberiano'),
(434, 'LY', 'LBY', 218, 'Libia', '�frica', NULL, 'LYD', 'Dinar libio'),
(438, 'LI', 'LIE', 423, 'Liechtenstein', 'Europa', NULL, NULL, NULL),
(440, 'LT', 'LTU', 370, 'Lituania', 'Europa', NULL, 'LTL', 'Litas lituano'),
(442, 'LU', 'LUX', 352, 'Luxemburgo', 'Europa', NULL, 'EUR', 'Euro'),
(446, 'MO', 'MAC', 853, 'Macao', 'Asia', NULL, 'MOP', 'Pataca de Macao'),
(450, 'MG', 'MDG', 261, 'Madagascar', '�frica', NULL, 'MGA', 'Ariary malgache'),
(454, 'MW', 'MWI', 265, 'Malaui', '�frica', NULL, 'MWK', 'Kwacha malauiano'),
(458, 'MY', 'MYS', 60, 'Malasia', 'Asia', NULL, 'MYR', 'Ringgit malayo'),
(462, 'MV', 'MDV', 960, 'Maldivas', 'Asia', NULL, 'MVR', 'Rufiyaa maldiva'),
(466, 'ML', 'MLI', 223, 'Mal�', '�frica', NULL, NULL, NULL),
(470, 'MT', 'MLT', 356, 'Malta', 'Europa', NULL, 'MTL', 'Lira maltesa'),
(474, 'MQ', 'MTQ', 0, 'Martinica', 'Am�rica', 'El Caribe', NULL, NULL),
(478, 'MR', 'MRT', 222, 'Mauritania', '�frica', NULL, 'MRO', 'Ouguiya mauritana'),
(480, 'MU', 'MUS', 230, 'Mauricio', '�frica', NULL, 'MUR', 'Rupia mauricia'),
(484, 'MX', 'MEX', 52, 'M�xico', 'Am�rica', 'Am�rica del Norte', 'MXN', 'Peso mexicano'),
(492, 'MC', 'MCO', 377, 'M�naco', 'Europa', NULL, NULL, NULL),
(496, 'MN', 'MNG', 976, 'Mongolia', 'Asia', NULL, 'MNT', 'Tughrik mongol'),
(498, 'MD', 'MDA', 373, 'Moldavia', 'Europa', NULL, ' DL', 'Leu moldavo'),
(499, 'ME', 'MNE', 382, 'Montenegro', 'Europa', NULL, NULL, NULL),
(500, 'MS', 'MSR', 1664, 'Montserrat', 'Am�rica', 'El Caribe', NULL, NULL),
(504, 'MA', 'MAR', 212, 'Marruecos', '�frica', NULL, 'MAD', 'Dirham marroqu�'),
(508, 'MZ', 'MOZ', 258, 'Mozambique', '�frica', NULL, 'MZM', 'Metical mozambique�o'),
(512, 'OM', 'OMN', 968, 'Om�n', 'Asia', NULL, 'OMR', 'Rial oman�'),
(516, 'NA', 'NAM', 264, 'Namibia', '�frica', NULL, 'NAD', 'D�lar namibio'),
(520, 'NR', 'NRU', 674, 'Nauru', 'Ocean�a', NULL, NULL, NULL),
(524, 'NP', 'NPL', 977, 'Nepal', 'Asia', NULL, 'NPR', 'Rupia nepalesa'),
(528, 'NL', 'NLD', 31, 'Pa�ses Bajos', 'Europa', NULL, 'EUR', 'Euro'),
(530, 'AN', 'ANT', 599, 'Antillas Holandesas', 'Am�rica', 'El Caribe', 'ANG', 'Flor�n antillano neerland�s'),
(533, 'AW', 'ABW', 297, 'Aruba', 'Am�rica', 'El Caribe', 'AWG', 'Flor�n arube�o'),
(540, 'NC', 'NCL', 687, 'Nueva Caledonia', 'Ocean�a', NULL, NULL, NULL),
(548, 'VU', 'VUT', 678, 'Vanuatu', 'Oce n�a', NULL, 'VUV', 'Vatu vanuatense'),
(554, 'NZ', 'NZL', 64, 'Nueva Zelanda', 'Ocean�a', NULL, 'NZD', 'D�lar neozeland�s'),
(558, 'NI', 'NIC', 505, 'Nicaragua', 'Am�rica', 'Am�rica Central', 'NIO', 'C�rdoba nicarag�ense'),
(562, 'NE', 'NER', 227, 'N�ger', '�frica', NULL, NULL, NULL),
(566, 'NG', 'NGA', 234, 'Nigeria', '�frica', NULL, 'NGN', 'Naira nigeriana'),
(570, 'NU', 'NIU', 683, 'Niue', 'Ocean�a', NULL, NULL, NULL),
(574, 'NF', 'NFK', 0, 'Isla Norfolk', 'Ocean�a', NULL, NULL, NULL),
(578, 'NO', 'NOR', 47, 'Noruega', 'Europa', NULL, 'NOK', 'Corona noruega'),
(580, 'MP', 'MNP', 1670, 'Islas Marianas del Norte', 'Ocean�a', NULL, NULL, NULL),
(581, 'UM', 'UMI', 0, 'Islas Ultramarinas de Estados Unidos', NULL, NULL, NULL, NULL),
(583, 'FM', 'FSM', 691, 'Micronesia', 'Ocean�a', NULL, NULL, NULL),
(584, 'MH', 'MHL', 692, 'Islas Marshall', 'Ocean�a', NULL, NULL, NULL),
(585, 'PW', 'PLW', 680, 'Palaos', 'Ocean�a', NULL, NULL, NULL),
(586, 'PK', 'PAK', 92, 'Pakist�n', 'Asia', NULL, 'PKR', 'Rupia pakistan�'),
(591, 'PA', 'PAN', 507, 'Panam�', 'Am�rica', 'Am�rica Central', 'PAB', 'Balboa paname�a'),
(598, 'PG', 'PNG', 675, 'Pap�a Nueva Guinea', 'Ocean�a', NULL, 'PGK', 'Kina de Pap�a Nueva Guinea'),
(600, 'PY', 'PRY', 595, 'Paraguay', 'Am�rica', 'Am�rica del Sur', 'PYG', 'Guaran� paraguayo'),
(604, 'PE', 'PER', 51, 'Per�', 'Am�rica', 'Am�rica del Sur', 'PEN', 'Nuevo sol peruano'),
(608, 'PH', 'PHL', 63, 'Filipinas', 'Asia', NULL, 'PHP', 'Peso filipino'),
(612, 'PN', 'PCN', 870, 'Islas Pitcairn', 'Ocean�a', NULL, NULL, NULL),
(616, 'PL', 'POL', 48, 'Polonia', 'Europa', NULL, 'PLN', 'zloty polaco'),
(620, 'PT', 'PRT', 351, 'Portugal', 'Europa', NULL, 'EUR', 'Euro'),
(624, 'GW', 'GNB', 245, 'Guinea-Bissau', '�frica', NULL, NULL, NULL),
(626, 'TL', 'TLS', 670, 'Timor Oriental', 'Asia', NULL, NULL, NULL),
(630, 'PR', 'PRI', 1, 'Puerto Rico', 'Am�rica', 'El Caribe', NULL, NULL),
(634, 'QA', 'QAT', 974, 'Qatar', 'Asia', NULL, 'QAR', 'Rial qatar�'),
(638  'RE', 'REU', 262, 'Reuni�n', '�fri