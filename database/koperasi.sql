-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2014 at 12:39 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `koperasi`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
  `idbarang` varchar(20) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`idbarang`, `namabarang`, `stock`, `harga`, `kategori`) VALUES
('aq001', 'Aqua 500ml', 10, 2500, 'Minuman'),
('pep24', 'Pepsodent', 200, 30000, 'Perlengkapan Mandi'),
('miz100', 'Mizone', 10, 5000, 'Minuman'),
('ind001', 'Indomie Kari', 90, 2000, 'makanan'),
('ind002', 'Indomie Soto', 90, 2000, 'makanan'),
('ind003', 'Indomie Ayam Bawang', 90, 1800, 'makanan'),
('bdr001', 'Bodrex Sakit kepala', 10, 2000, 'obat');

-- --------------------------------------------------------

--
-- Table structure for table `kategoribarang`
--

CREATE TABLE IF NOT EXISTS `kategoribarang` (
  `kategoribarang` varchar(20) NOT NULL,
  `deskripsibarang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategoribarang`
--

INSERT INTO `kategoribarang` (`kategoribarang`, `deskripsibarang`) VALUES
('makanan', 'Barang yang bisa dimakan'),
('Minuman', 'Barang cair yang dapat diminum'),
('Perlengkapan Mandi', 'Perlengkapan yang dipakai mandi'),
('obat', 'obat-obatn'),
('1', ''),
('minuman', 'barang yang bisa dimakan');

-- --------------------------------------------------------

--
-- Table structure for table `masterpenjulan`
--

CREATE TABLE IF NOT EXISTS `masterpenjulan` (
  `idpenjualan` varchar(10) NOT NULL,
  `iduser` varchar(20) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `namapembeli` varchar(50) NOT NULL,
  `alamatpembeli` text NOT NULL,
  `jumlahbarang` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `masterpenjulan`
--

INSERT INTO `masterpenjulan` (`idpenjualan`, `iduser`, `namabarang`, `namapembeli`, `alamatpembeli`, `jumlahbarang`, `total`) VALUES
('109875', 'Tri Siswanto', 'odol', 'Muhammad Ridwan', 'Kebagusan II', 2, 4000),
('109875', 'Tri Siswanto', 'Aqua 500ml', 'Muhammad Ridwan', 'Kebagusan II', 1, 2500),
('109876', 'Tri Siswanto', 'odol', 'Rudihamzah', 'Jl. Rawa Minyak', 10, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
  `idpenjualan` varchar(12) NOT NULL,
  `user` varchar(50) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`idpenjualan`, `user`, `total`) VALUES
('109876', 'Tri Siswanto', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(20) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `tlp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `password`, `alamat`, `tlp`) VALUES
('201143500198', 'Tri Siswanto', 'iwanwina', 'Jati Padang', '08119408636'),
('12345678', 'Rizki Gamal', '12345', 'Jati Padang', '0978292929');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
