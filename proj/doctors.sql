-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Апр 28 2020 г., 18:40
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `doctors`
--

-- --------------------------------------------------------

--
-- Структура таблицы `mydb`
--

CREATE TABLE `mydb` (
  `id` int(11) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` int(11) NOT NULL,
  `userType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `mydb`
--

INSERT INTO `mydb` (`id`, `firstName`, `lastName`, `login`, `password`, `userType`) VALUES
(1, 'Saule', 'Medeu', 'saule', 123, 'patient'),
(2, 'Aitoraz', 'Zhumadil', 'aito', 201, 'patient'),
(3, 'Aimen', 'Aslanbekovna', 'aimesha', 199, 'patient'),
(4, 'Aiat', 'Zhumadil', 'aiat', 159, 'patient'),
(5, 'Aitbek', 'Zhumash', 'aiteke', 753, 'patient'),
(6, 'Aslanbek', 'Medeuyly', 'aslan', 753, 'patient'),
(7, 'Akzhan', 'Aztaukeiova', 'aksh', 842, 'doctor'),
(9, 'Alma', 'Myrzabekova', 'almusha', 520, 'doctor'),
(10, 'Almas', 'Almasov', 'aleke', 489, 'patient'),
(11, 'Azamat', 'Azamatov', 'azeke', 145, 'doctor'),
(12, 'Daulet', 'Dauletov', 'dauka', 465, 'doctor'),
(13, 'Erulan', 'Erulanov', 'ereke', 753, 'patient'),
(14, 'Moldir', 'Zhanseitova', 'molli', 302, 'doctor'),
(15, 'Murat', 'Muratov', 'mureke', 751, 'patient'),
(16, 'Rakhat', 'Tastybaiev', 'raha', 657, 'doctor'),
(17, 'Sanzhar', 'Nigmetov', 'sanzhik', 345, 'doctor'),
(18, 'Sunkar', 'Nursultan', 'astana', 568, 'patient'),
(19, 'Togzhan', 'Tozhanova', 'toxa', 901, 'patient'),
(20, 'Tolegen', 'Tolegenov', 'tolya', 359, 'patient'),
(21, 'Zharas', 'Zharasov', 'zhara', 667, 'patient'),
(22, 'Zhassybai', 'Zhassov', 'zhass', 759, 'patient'),
(23, 'Zhumabek', 'Zhumabekov', 'zhuma', 766, 'patient'),
(24, 'Aryn', 'Arynov', 'areke', 895, 'patient'),
(25, 'Aiko', 'Aikov', 'aikaa', 784, 'patient'),
(26, 'Maksat', 'Pazyl', 'maks', 555, 'patient'),
(27, 'Amina', 'Bolysova', 'aminoka', 444, 'patient'),
(28, 'Arlan', 'Arlanov', 'arlonya', 666, 'doctor'),
(29, 'Ramazan', 'Sovkhozov', 'roma', 777, 'patient'),
(30, 'Aidos', 'Aidosov', 'aidoo', 331, 'patient'),
(31, 'Madina', 'Madinova', 'madii', 221, 'patient'),
(32, 'Alina', 'Alinova', 'aliii', 445, 'patient'),
(33, 'Alishaer', 'Alisherov', 'Alish', 883, 'doctor'),
(34, 'Dulatbek', 'Dulatbekov', 'dula', 778, 'doctor'),
(35, 'Baha', 'Bahayev', 'bake', 336, 'patient'),
(36, 'Nurlybek', 'Nurlybekov', 'nurly', 255, 'patient'),
(37, 'Nurali', 'Nuraliev', 'nura', 758, 'patient'),
(38, 'Nurakhmet', 'Nurakhmetov', 'nureke', 312, 'doctor'),
(39, 'Erasyl', 'Erasylov', 'era', 111, 'doctor'),
(40, 'Dias', 'Diasov', 'diko', 496, 'patient'),
(41, 'Nurbak', 'Nurbakov', 'nuriii', 100, 'patient'),
(42, 'Aldiyar', 'Aldiarov', 'aldik', 222, 'patient'),
(43, 'Bekulan', 'Dautov', 'beka', 773, 'doctor'),
(44, 'Askhat', 'Dosybai', 'aseke', 551, 'doctor'),
(45, 'Akerke', 'Zhunisova', 'akosh', 313, 'patient'),
(46, 'Arman', 'Amantayev', 'arman', 102, 'patient'),
(47, 'Gulnaz', 'Turgymbekova', 'gulya', 203, 'patient'),
(48, 'Aigerim', 'Beskempirova', 'aika', 737, 'patient'),
(49, 'Zhandaulet', 'Tursynbek', 'zhanda', 606, 'doctor'),
(50, 'Nurila', 'Parket', 'nuri', 404, 'patient'),
(51, 'Sultan', 'Makyshbek', 'sula', 505, 'patient'),
(52, 'Islana', 'Islanova', 'isla', 707, 'patient'),
(53, 'Araika', 'Dauletova', 'arai', 909, 'patient');

-- --------------------------------------------------------

--
-- Структура таблицы `problems`
--

CREATE TABLE `problems` (
  `id` int(11) NOT NULL,
  `problem` varchar(30) NOT NULL,
  `nameDoctor` varchar(30) NOT NULL,
  `namePatient` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `problems`
--

INSERT INTO `problems` (`id`, `problem`, `nameDoctor`, `namePatient`) VALUES
(3, 'Headache', 'Akzhan', 'Aimen'),
(10, 'eyes', 'Alma', 'Almas'),
(23, 'Brain', 'Erasyl', 'Zhumabek'),
(24, 'Insomnia', 'Moldir', 'Aryn'),
(29, 'Insomnia', 'Moldir', 'Ramazan'),
(40, 'Headache', 'Akzhan', 'Dias'),
(46, 'Headproblem', 'Zhandaulet', 'Arman'),
(53, 'Allergy', 'Bekulan', 'Araika');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `mydb`
--
ALTER TABLE `mydb`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `problems`
--
ALTER TABLE `problems`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `mydb`
--
ALTER TABLE `mydb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT для таблицы `problems`
--
ALTER TABLE `problems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
