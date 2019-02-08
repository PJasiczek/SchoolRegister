-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 08 Lut 2019, 01:38
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `school_register`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `absence`
--

CREATE TABLE `absence` (
  `absence_id` int(11) NOT NULL,
  `pupil_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `class`
--

CREATE TABLE `class` (
  `class_id` int(11) NOT NULL,
  `educator_id` int(11) NOT NULL,
  `name` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `educator`
--

CREATE TABLE `educator` (
  `educator_id` int(11) NOT NULL,
  `PESEL` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `first_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `last_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `contact_number` varchar(9) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `academic_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grade`
--

CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `grades` int(30) DEFAULT NULL,
  `grade` int(32) DEFAULT NULL,
  `pupil_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `note`
--

CREATE TABLE `note` (
  `note_id` int(11) NOT NULL,
  `note` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `pupil_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pupil`
--

CREATE TABLE `pupil` (
  `id` int(11) NOT NULL,
  `PESEL` varchar(11) COLLATE utf16_polish_ci NOT NULL,
  `first_name` varchar(10) COLLATE utf16_polish_ci NOT NULL,
  `last_name` varchar(20) COLLATE utf16_polish_ci NOT NULL,
  `class_id` int(11) NOT NULL,
  `address` varchar(50) COLLATE utf16_polish_ci NOT NULL,
  `mother_name` varchar(10) COLLATE utf16_polish_ci DEFAULT NULL,
  `father_name` varchar(10) COLLATE utf16_polish_ci DEFAULT NULL,
  `contact_number` varchar(9) COLLATE utf16_polish_ci DEFAULT NULL,
  `sex` varchar(11) COLLATE utf16_polish_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `register_number` tinyint(3) UNSIGNED NOT NULL,
  `password` varchar(30) COLLATE utf16_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `subject_name` int(20) NOT NULL,
  `acronym` int(5) NOT NULL,
  `password` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `PESEL` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `first_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `last_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `contact_number` varchar(9) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `academic_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`absence_id`),
  ADD KEY `pupil_id` (`pupil_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `class_id` (`class_id`);

--
-- Indeksy dla tabeli `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `educator_id` (`educator_id`);

--
-- Indeksy dla tabeli `educator`
--
ALTER TABLE `educator`
  ADD PRIMARY KEY (`educator_id`);

--
-- Indeksy dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `pupil_id` (`pupil_id`);

--
-- Indeksy dla tabeli `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`note_id`),
  ADD KEY `pupil_id` (`pupil_id`);

--
-- Indeksy dla tabeli `pupil`
--
ALTER TABLE `pupil`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_id` (`class_id`);

--
-- Indeksy dla tabeli `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `class_id` (`class_id`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indeksy dla tabeli `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `absence`
--
ALTER TABLE `absence`
  MODIFY `absence_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `grade`
--
ALTER TABLE `grade`
  MODIFY `grade_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `note`
--
ALTER TABLE `note`
  MODIFY `note_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  ADD CONSTRAINT `absence_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  ADD CONSTRAINT `absence_ibfk_3` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `absence_ibfk_4` FOREIGN KEY (`pupil_id`) REFERENCES `pupil` (`id`);

--
-- Ograniczenia dla tabeli `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`educator_id`) REFERENCES `educator` (`educator_id`);

--
-- Ograniczenia dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`pupil_id`) REFERENCES `pupil` (`id`);

--
-- Ograniczenia dla tabeli `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`pupil_id`) REFERENCES `pupil` (`id`);

--
-- Ograniczenia dla tabeli `pupil`
--
ALTER TABLE `pupil`
  ADD CONSTRAINT `pupil_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`);

--
-- Ograniczenia dla tabeli `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  ADD CONSTRAINT `subject_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
