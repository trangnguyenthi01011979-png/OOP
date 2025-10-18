-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 16, 2025 lúc 05:22 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `test`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cauhoi`
--

CREATE TABLE `cauhoi` (
  `MC` int(2) NOT NULL,
  `noidungch` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `A` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `B` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `C` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `D` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DapAn` char(10) NOT NULL,
  `loaida` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `cauhoi`
--

INSERT INTO `cauhoi` (`MC`, `noidungch`, `A`, `B`, `C`, `D`, `DapAn`, `loaida`) VALUES
(1, 'ai dẹp trai nhất', 'D', 'H', 'H', 'K', 'A', 1),
(2, 'Trong Java, từ khóa nào dùng để kế thừa lớp?', 'super', 'extends', 'this', 'inherits', 'AB', 2),
(3, 'Phép chia 15 / 4 trong Java (kiểu int) cho kết quả là?', '3.75', '4', '3', 'Lỗi chia kiểu', 'C', 1),
(4, 'Trong SQL, câu lệnh nào dùng để lấy dữ liệu?', 'INSERT', 'SELECT', 'UPDATE', 'DELETE', 'BC', 2),
(5, 'Trong HTML, thẻ nào để chèn hình ảnh?', '<image>', '<img>', '<picture>', '<src>', 'B', 1),
(6, 'Kết quả của 5 + 2 * 3 là?', '21', '11', '13', '10', 'B', 1),
(7, 'Trong lập trình hướng đối tượng, \"Encapsulation\" có nghĩa là gì?', 'Gộp dữ liệu và phương thức lại', 'Chia nhỏ chương trình', 'Kế thừa', 'Đa hình', 'A', 1),
(8, 'Trong MySQL, để xóa bảng dùng lệnh nào?', 'DROP TABLE', 'DELETE TABLE', 'REMOVE TABLE', 'CLEAR TABLE', 'A', 1),
(9, 'Kiểu dữ liệu nào lưu chuỗi ký tự trong Java?', 'int', 'String', 'char', 'float', 'B', 1),
(10, 'Đâu là cú pháp đúng để khai báo mảng trong Java?', 'int[] arr;', 'int arr[];', 'Cả hai đều đúng', 'arr int[];', 'C', 1),
(11, 'Câu lệnh SQL nào dùng để cập nhật dữ liệu?', 'INSERT', 'UPDATE', 'ALTER', 'CHANGE', 'B', 1),
(12, '5 % 2 trong Java cho kết quả là?', '1', '2', '2.5', '0', 'A', 1),
(13, 'Biến static trong Java thuộc về?', 'Đối tượng', 'Lớp', 'Phương thức', 'Biến cục bộ', 'B', 1),
(14, 'Trong SQL, hàm COUNT() dùng để?', 'Đếm số bản ghi', 'Tính tổng', 'Lấy giá trị lớn nhất', 'Lấy giá trị nhỏ nhất', 'A', 1),
(15, 'Kết quả của 2 + 2 * 2 = ?', '6', '8', '4', '2', 'A', 1),
(16, 'Trong MySQL, khóa chính được định nghĩa bằng?', 'PRIMARY KEY', 'FOREIGN KEY', 'UNIQUE KEY', 'IDENTITY', 'A', 1),
(17, 'Phép toán nào có độ ưu tiên cao nhất?', '+', '-', '*', '=', 'C', 1),
(18, 'Kết quả của biểu thức (true && false) || true là?', 'true', 'false', 'error', 'null', 'A', 1),
(19, 'Phần mở rộng của file Java là?', '.jav', '.java', '.class', '.jar', 'B', 1),
(20, 'Phương thức main() trong Java phải có dạng nào?', 'public void main(String args[])', 'static void main(String args[])', 'public static void main(String[] args)', 'void main()', 'C', 1),
(21, 'Trong SQL, để sắp xếp dữ liệu dùng từ khóa nào?', 'GROUP BY', 'ORDER BY', 'SORT', 'ARRANGE', 'B', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctdt`
--

CREATE TABLE `ctdt` (
  `IDCau` int(3) NOT NULL,
  `MC` int(2) NOT NULL,
  `MD` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `ctdt`
--

INSERT INTO `ctdt` (`IDCau`, `MC`, `MD`) VALUES
(27, 1, 1),
(28, 5, 1),
(29, 14, 1),
(30, 18, 1),
(31, 11, 1),
(32, 1, 2),
(33, 5, 2),
(34, 13, 2),
(35, 18, 2),
(36, 8, 2),
(37, 4, 1),
(38, 12, 1),
(39, 21, 1),
(40, 16, 1),
(41, 7, 1),
(42, 2, 2),
(43, 15, 2),
(44, 20, 2),
(45, 10, 2),
(46, 17, 2),
(47, 15, 1),
(48, 19, 1),
(49, 3, 1),
(50, 11, 3),
(51, 19, 3),
(52, 10, 3),
(53, 5, 3),
(54, 16, 3),
(55, 21, 3),
(56, 9, 3),
(57, 6, 3),
(58, 1, 3),
(59, 1, 4),
(60, 13, 4),
(61, 10, 4),
(62, 16, 4),
(63, 19, 4),
(64, 21, 4),
(65, 18, 4),
(66, 15, 4),
(67, 17, 4),
(68, 20, 4),
(69, 12, 4),
(70, 6, 4),
(71, 2, 4),
(72, 3, 4),
(73, 4, 4),
(74, 5, 4),
(75, 8, 4),
(76, 7, 4),
(77, 9, 4),
(78, 11, 4),
(79, 14, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dakt`
--

CREATE TABLE `dakt` (
  `MaKT` int(11) NOT NULL,
  `MD` int(2) NOT NULL,
  `MaTaiKhoan` varchar(10) NOT NULL,
  `TrangThai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dakt`
--

INSERT INTO `dakt` (`MaKT`, `MD`, `MaTaiKhoan`, `TrangThai`) VALUES
(6, 1, '7273897839', 'Đã làm!'),
(7, 1, '0610227262', 'Đã làm!');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dang_nhap`
--

CREATE TABLE `dang_nhap` (
  `MaTaiKhoan` varchar(10) NOT NULL,
  `TenDangNhap` varchar(50) NOT NULL,
  `MatKhau` varchar(1000) NOT NULL,
  `PhanLoai` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dang_nhap`
--

INSERT INTO `dang_nhap` (`MaTaiKhoan`, `TenDangNhap`, `MatKhau`, `PhanLoai`) VALUES
('0610227262', 'NVHien191005', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'NT'),
('4182636425', 'HQHuy140405', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'NT'),
('4335297847', 'VTHa020105', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'NT'),
('4397759178', 'DTYen1111', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'GV'),
('6110035022', 'LMThu250207', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', 'NT'),
('7273897839', 'LMDang151105', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'NT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dethi`
--

CREATE TABLE `dethi` (
  `MD` int(2) NOT NULL,
  `NoidungDeThi` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ThoiGian` int(11) NOT NULL,
  `NgayTao` date DEFAULT NULL,
  `NgayThi` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `dethi`
--

INSERT INTO `dethi` (`MD`, `NoidungDeThi`, `ThoiGian`, `NgayTao`, `NgayThi`) VALUES
(1, 'Đề thi Tổ Hợp', 15, '2025-10-14', '2025-10-14'),
(2, 'Đề Thi Tổ Hợp 2', 15, '2025-10-14', '2025-10-16'),
(3, 'Đề Thi Giáo Dục Thể Chất', 30, '2000-10-09', '2025-11-15'),
(4, 'Đề Thi Mạng Máy Tính', 15, '2000-11-13', '2025-05-20');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsuthi`
--

CREATE TABLE `lichsuthi` (
  `MLS` int(11) NOT NULL,
  `MaTaiKhoan` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MaDe` int(2) NOT NULL,
  `KQ` float NOT NULL,
  `socaudung` int(11) NOT NULL,
  `NgayThi` date NOT NULL,
  `ThoiGianLam` varchar(10) NOT NULL,
  `TTCT` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `lichsuthi`
--

INSERT INTO `lichsuthi` (`MLS`, `MaTaiKhoan`, `MaDe`, `KQ`, `socaudung`, `NgayThi`, `ThoiGianLam`, `TTCT`) VALUES
(16, '0610227262', 1, 6.9, 9, '2025-10-14', '0:0:18', 'Câu 1: ai dẹp trai nhất\n   Kết quả: Sai\n   Bạn chọn: B\n   Đáp án đúng: A\n\nCâu 2: Trong HTML, thẻ nào để chèn hình ảnh?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 3: Trong SQL, hàm COUNT() dùng để?\n   Kết quả: Sai\n   Bạn chọn: C\n   Đáp án đúng: A\n\nCâu 4: Kết quả của biểu thức (true && false) || true là?\n   Kết quả: Đúng\n   Bạn chọn: A\n   Đáp án đúng: A\n\nCâu 5: Câu lệnh SQL nào dùng để cập nhật dữ liệu?\n   Kết quả: Sai\n   Bạn chọn: C\n   Đáp án đúng: B\n\nCâu 6: Trong SQL, câu lệnh nào dùng để lấy dữ liệu?\n   Kết quả: Sai\n   Bạn chọn: B\n   Đáp án đúng: AB\n\nCâu 7: 5 % 2 trong Java cho kết quả là?\n   Kết quả: Đúng\n   Bạn chọn: A\n   Đáp án đúng: A\n\nCâu 8: Trong SQL, để sắp xếp dữ liệu dùng từ khóa nào?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 9: Trong MySQL, khóa chính được định nghĩa bằng?\n   Kết quả: Đúng\n   Bạn chọn: A\n   Đáp án đúng: A\n\nCâu 10: Trong lập trình hướng đối tượng, \"Encapsulation\" có nghĩa là gì?\n   Kết quả: Đúng\n   Bạn chọn: A\n   Đáp án đúng: A\n\nCâu 11: Kết quả của 2 + 2 * 2 = ?\n   Kết quả: Đúng\n   Bạn chọn: A\n   Đáp án đúng: A\n\nCâu 12: Phần mở rộng của file Java là?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 13: Phép chia 15 / 4 trong Java (kiểu int) cho kết quả là?\n   Kết quả: Đúng\n   Bạn chọn: C\n   Đáp án đúng: C\n\n-----------------------------------\nTỔNG KẾT:\n   Số câu đúng: 9\n   Số câu sai: 4\n   Tổng số câu: 13\n'),
(17, '7273897839', 2, 0, 0, '2025-10-16', '0:0:3', 'Câu 1: ai dẹp trai nhất\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: A\n\nCâu 2: Trong HTML, thẻ nào để chèn hình ảnh?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: B\n\nCâu 3: Biến static trong Java thuộc về?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: B\n\nCâu 4: Kết quả của biểu thức (true && false) || true là?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: A\n\nCâu 5: Trong MySQL, để xóa bảng dùng lệnh nào?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: A\n\nCâu 6: Trong Java, từ khóa nào dùng để kế thừa lớp?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: AB\n\nCâu 7: Kết quả của 2 + 2 * 2 = ?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: A\n\nCâu 8: Phương thức main() trong Java phải có dạng nào?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: C\n\nCâu 9: Đâu là cú pháp đúng để khai báo mảng trong Java?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: C\n\nCâu 10: Phép toán nào có độ ưu tiên cao nhất?\n   Bạn chưa chọn đáp án nào.\n   Đáp án đúng: C\n\n-----------------------------------\nTỔNG KẾT:\n   Số câu đúng: 0\n   Số câu sai: 10\n   Tổng số câu: 10\n'),
(18, '7273897839', 3, 4.4, 4, '2025-11-15', '0:0:11', 'Câu 1: Câu lệnh SQL nào dùng để cập nhật dữ liệu?\n   Kết quả: Sai\n   Bạn chọn: A\n   Đáp án đúng: B\n\nCâu 2: Phần mở rộng của file Java là?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 3: Đâu là cú pháp đúng để khai báo mảng trong Java?\n   Kết quả: Đúng\n   Bạn chọn: C\n   Đáp án đúng: C\n\nCâu 4: Trong HTML, thẻ nào để chèn hình ảnh?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 5: Trong MySQL, khóa chính được định nghĩa bằng?\n   Kết quả: Sai\n   Bạn chọn: C\n   Đáp án đúng: A\n\nCâu 6: Trong SQL, để sắp xếp dữ liệu dùng từ khóa nào?\n   Kết quả: Đúng\n   Bạn chọn: B\n   Đáp án đúng: B\n\nCâu 7: Kiểu dữ liệu nào lưu chuỗi ký tự trong Java?\n   Kết quả: Sai\n   Bạn chọn: A\n   Đáp án đúng: B\n\nCâu 8: Kết quả của 5 + 2 * 3 là?\n   Kết quả: Sai\n   Bạn chọn: C\n   Đáp án đúng: B\n\nCâu 9: ai dẹp trai nhất\n   Kết quả: Sai\n   Bạn chọn: D\n   Đáp án đúng: A\n\n-----------------------------------\nTỔNG KẾT:\n   Số câu đúng: 4\n   Số câu sai: 5\n   Tổng số câu: 9\n');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lop`
--

CREATE TABLE `lop` (
  `MaLop` int(11) NOT NULL,
  `TenLop` varchar(20) DEFAULT NULL,
  `MaNganh` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lop`
--

INSERT INTO `lop` (`MaLop`, `TenLop`, `MaNganh`) VALUES
(2, 'B023TT1', 'TT'),
(3, 'B023TT2', 'TT'),
(4, 'B023NA1', 'NA'),
(5, 'B023NA2', 'NA'),
(6, 'B024PT1', 'PT'),
(7, 'B024PT2', 'PT'),
(8, 'B023QT1', 'QT'),
(9, 'B023QT2', 'QT'),
(10, 'B023NH1', 'NH'),
(11, 'B023NH2', 'NH'),
(12, 'B023KT4', 'KT'),
(15, 'B025PT1', 'PT');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nganh`
--

CREATE TABLE `nganh` (
  `MaNganh` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenNganh` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `nganh`
--

INSERT INTO `nganh` (`MaNganh`, `TenNganh`) VALUES
('KT', 'Kinh Tế'),
('NA', 'Ngôn Ngữ Anh'),
('NH', 'Tài Chính Ngân Hàng'),
('PT', 'Truyền Thông Đa Phương Tiện'),
('QT', 'Quản Trị Kinh Doanh'),
('TT', 'Công Nghệ Thông Tin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sodienthoai`
--

CREATE TABLE `sodienthoai` (
  `MaTaiKhoan` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SDT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `sodienthoai`
--

INSERT INTO `sodienthoai` (`MaTaiKhoan`, `SDT`) VALUES
('0610227262', '5868009918'),
('4335297847', '4789018224'),
('7273897839', '3214532521');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thi`
--

CREATE TABLE `thi` (
  `MD` int(2) NOT NULL,
  `MaTaiKhoan` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thi`
--

INSERT INTO `thi` (`MD`, `MaTaiKhoan`) VALUES
(4, '0610227262'),
(4, '4182636425'),
(3, '4335297847'),
(2, '6110035022'),
(1, '7273897839');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ttnguoithi`
--

CREATE TABLE `ttnguoithi` (
  `MaTaiKhoan` varchar(10) NOT NULL,
  `HoTen` varchar(50) NOT NULL,
  `Malop` int(11) NOT NULL,
  `MaDeThi` int(2) NOT NULL DEFAULT 0,
  `NgaySinh` date NOT NULL,
  `MaNganh` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SDT` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ttnguoithi`
--

INSERT INTO `ttnguoithi` (`MaTaiKhoan`, `HoTen`, `Malop`, `MaDeThi`, `NgaySinh`, `MaNganh`, `SDT`) VALUES
('0610227262', 'Nguyễn Văn Hiền', 3, 0, '2005-10-19', 'TT', '0868009918'),
('4182636425', 'Hồ Quốc Huy', 3, 0, '2005-04-14', 'TT', '4328490321'),
('4335297847', 'Võ Trí Hà', 2, 0, '2005-01-02', 'TT', '4789018224'),
('6110035022', 'Lê Minh Thư', 12, 0, '2007-02-25', 'KT', '4323218941'),
('7273897839', 'Lê Minh Đăng', 3, 0, '2005-11-15', 'TT', '5868009918');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cauhoi`
--
ALTER TABLE `cauhoi`
  ADD PRIMARY KEY (`MC`);

--
-- Chỉ mục cho bảng `ctdt`
--
ALTER TABLE `ctdt`
  ADD PRIMARY KEY (`IDCau`),
  ADD KEY `FK_cauhoi_ctdt` (`MC`),
  ADD KEY `FK_Dethi_ctdt` (`MD`);

--
-- Chỉ mục cho bảng `dakt`
--
ALTER TABLE `dakt`
  ADD PRIMARY KEY (`MaKT`),
  ADD KEY `fk_dakt_dethi` (`MD`),
  ADD KEY `fk_dakt_dang_nhap` (`MaTaiKhoan`);

--
-- Chỉ mục cho bảng `dang_nhap`
--
ALTER TABLE `dang_nhap`
  ADD PRIMARY KEY (`MaTaiKhoan`),
  ADD UNIQUE KEY `TenDangNhap` (`TenDangNhap`);

--
-- Chỉ mục cho bảng `dethi`
--
ALTER TABLE `dethi`
  ADD PRIMARY KEY (`MD`);

--
-- Chỉ mục cho bảng `lichsuthi`
--
ALTER TABLE `lichsuthi`
  ADD PRIMARY KEY (`MLS`),
  ADD KEY `fk_lst_dt` (`MaDe`),
  ADD KEY `fk_lst_dn` (`MaTaiKhoan`) USING BTREE;

--
-- Chỉ mục cho bảng `lop`
--
ALTER TABLE `lop`
  ADD PRIMARY KEY (`MaLop`),
  ADD KEY `FK_Lop_Nganh` (`MaNganh`);

--
-- Chỉ mục cho bảng `nganh`
--
ALTER TABLE `nganh`
  ADD PRIMARY KEY (`MaNganh`);

--
-- Chỉ mục cho bảng `sodienthoai`
--
ALTER TABLE `sodienthoai`
  ADD PRIMARY KEY (`SDT`),
  ADD KEY `fk_sdt_dn` (`MaTaiKhoan`);

--
-- Chỉ mục cho bảng `thi`
--
ALTER TABLE `thi`
  ADD KEY `FK_thi_dethi` (`MD`),
  ADD KEY `FK_thi_dangnhap` (`MaTaiKhoan`);

--
-- Chỉ mục cho bảng `ttnguoithi`
--
ALTER TABLE `ttnguoithi`
  ADD PRIMARY KEY (`MaTaiKhoan`,`Malop`,`MaDeThi`,`MaNganh`) USING BTREE,
  ADD KEY `fk_ttn_lop` (`Malop`),
  ADD KEY `fk_ttn_nganh` (`MaNganh`),
  ADD KEY `SDT` (`SDT`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cauhoi`
--
ALTER TABLE `cauhoi`
  MODIFY `MC` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `ctdt`
--
ALTER TABLE `ctdt`
  MODIFY `IDCau` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT cho bảng `dakt`
--
ALTER TABLE `dakt`
  MODIFY `MaKT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `dethi`
--
ALTER TABLE `dethi`
  MODIFY `MD` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `lichsuthi`
--
ALTER TABLE `lichsuthi`
  MODIFY `MLS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `lop`
--
ALTER TABLE `lop`
  MODIFY `MaLop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctdt`
--
ALTER TABLE `ctdt`
  ADD CONSTRAINT `FK_Dethi_ctdt` FOREIGN KEY (`MD`) REFERENCES `dethi` (`MD`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_cauhoi_ctdt` FOREIGN KEY (`MC`) REFERENCES `cauhoi` (`MC`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `dakt`
--
ALTER TABLE `dakt`
  ADD CONSTRAINT `fk_dakt_dang_nhap` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_dakt_dethi` FOREIGN KEY (`MD`) REFERENCES `dethi` (`MD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `lop`
--
ALTER TABLE `lop`
  ADD CONSTRAINT `FK_Lop_Nganh` FOREIGN KEY (`MaNganh`) REFERENCES `nganh` (`MaNganh`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `sodienthoai`
--
ALTER TABLE `sodienthoai`
  ADD CONSTRAINT `fk_sdt_dn` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `thi`
--
ALTER TABLE `thi`
  ADD CONSTRAINT `FK_thi_dangnhap` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_thi_dethi` FOREIGN KEY (`MD`) REFERENCES `dethi` (`MD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `ttnguoithi`
--
ALTER TABLE `ttnguoithi`
  ADD CONSTRAINT `FK_dn_ttnt` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_MaTaiKhoan` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tt_tk` FOREIGN KEY (`MaTaiKhoan`) REFERENCES `dang_nhap` (`MaTaiKhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ttn_lop` FOREIGN KEY (`Malop`) REFERENCES `lop` (`MaLop`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ttn_nganh` FOREIGN KEY (`MaNganh`) REFERENCES `nganh` (`MaNganh`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
