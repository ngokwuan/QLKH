package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.tktq;
import model.sanPham;

public class thongKeTongQuatDAO {
    private static final Logger LOGGER = Logger.getLogger(thongKeTongQuatDAO.class.getName());
    private MyConnection myConnect = new MyConnection();

    public ArrayList<tktq> getListTKTQ(int year) {
        ArrayList<tktq> list = new ArrayList<>();
        String query = "SELECT * FROM kho.doanhthu_quy WHERE Nam = ?";
        Connection conn = myConnect.openConnectDB();
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, year);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int Nam = resultSet.getInt("Nam");                    
                        int Quy = resultSet.getInt("Quy");
                        double DoanhThu = (resultSet.getDouble("DoanhThu"));
                        double TongDoanhThuQuy = (resultSet.getDouble("TongDoanhThuQuy"));

                        tktq sp = new tktq();
                        sp.setNam(Nam);
                        sp.setDoanhThu(DoanhThu);
                        sp.setQuy(Quy);
                        sp.setTongDoanhThuQuy(TongDoanhThuQuy);
                        list.add(sp);
                    }
                    LOGGER.log(Level.INFO, "Data retrieval successful");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error while retrieving data", e);
            } finally {
                myConnect.closeConnectDB();
            }
        } else {
            LOGGER.log(Level.SEVERE, "Could not establish connection to database");
        }

        return list;
    }
    
    public ArrayList<sanPham> getList() {
        ArrayList<sanPham> list = new ArrayList<>();
        String query = "SELECT * FROM sanpham";
        Connection conn = myConnect.openConnectDB();
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int maSP = resultSet.getInt("MaSP");
                    String tenSP = resultSet.getString("TenSP");
                    int soLuongNhap = resultSet.getInt("SoLuongNhap");
                    int tonKho = resultSet.getInt("TonKho");
                    float giaSP = parseFloatWithoutComma(resultSet.getString("GiaSP"));
                    String ngayXuat = resultSet.getString("NgayXuat");

                    sanPham sp = new sanPham();
                    sp.setMaSP(maSP);
                    sp.setTenSP(tenSP);
                    sp.setSoLuongNhap(soLuongNhap);
                    sp.setTonKho(tonKho);
                    sp.setGiaSP(giaSP);
             

                    list.add(sp);
                }
                LOGGER.log(Level.INFO, "Data retrieval successful");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error while retrieving data", e);
            } finally {
                myConnect.closeConnectDB();
            }
        } else {
            LOGGER.log(Level.SEVERE, "Could not establish connection to database");
        }

        return list;
    }


    private float parseFloatWithoutComma(String numberString) {
        // Remove commas and parse to float
        String plainNumber = numberString.replace(",", "");
        return Float.parseFloat(plainNumber);
    }

}
