package com.demo.service.impl;

import com.demo.service.XuLyDoTuongDong;
import org.springframework.stereotype.Service;

@Service
public class XuLyDoTuongDongImpl implements XuLyDoTuongDong {
    @Override
    public double dienTich(Double dienTichHandle, Double dienTichSource) {

        return dienTichHandle < dienTichSource ?
                Math.abs((double) 1 - Math.abs(dienTichHandle - dienTichSource) / dienTichSource) :
                Math.abs((double) 1 - Math.abs(dienTichSource - dienTichHandle) / dienTichHandle);
    }

    @Override
    public double quanHuyen(String quanHuyenHandle, String quanHuyenSource) {
        return quanHuyenHandle.equals(quanHuyenSource) ? 1 : 0;
    }

    @Override
    public double soPhongNgu(int soPhongNguHandle, int soPhongNguSource) {
        return soPhongNguHandle < soPhongNguSource ?
                Math.abs((double) 1 - Math.abs(soPhongNguHandle - soPhongNguSource) / (double) soPhongNguSource):
                Math.abs((double) 1 - Math.abs(soPhongNguSource - soPhongNguHandle) / (double) soPhongNguHandle);
    }

    @Override
    public double soPhongWc(int soPhongWcHandle, int soPhongWcSource) {
        return soPhongWcHandle < soPhongWcSource ?
                Math.abs((double) 1 - Math.abs(soPhongWcHandle - soPhongWcSource) / (double) soPhongWcSource):
                Math.abs((double) 1 - Math.abs(soPhongWcSource - soPhongWcHandle) / (double) soPhongWcHandle);
    }

    @Override
    public double huong(String huongHandle, String huongSource) {
        switch (huongHandle) {
            case "Đông": {
                switch (huongSource) {
                    case "Đông": return 1;
                    case "Đông Bắc":
                    case "Đông Nam":
                        return 0.5;
                    default: return 0;
                }
            }
            case "Tây": {
                switch (huongSource) {
                    case "Tây": return 1;
                    case "Tây Nam":
                    case "Tây Bắc":
                        return 0.5;
                    default: return 0;
                }
            }
            case "Nam": {
                switch (huongSource) {
                    case "Nam": return 1;
                    case "Đông Nam":
                    case "Tây Nam":
                        return 0.5;
                    default: return 0;
                }
            }
            case "Bắc": {
                switch (huongSource) {
                    case "Bắc": return 1;
                    case "Đông Bắc":
                    case "Tây Bắc":
                        return 0.5;
                    default: return 0;
                }
            }
            case "Đông Bắc": {
                switch (huongSource) {
                    case "Đông":
                    case "Bắc":
                        return 0.5;
                    case "Đông Bắc": return 1;
                    default: return 0;
                }
            }
            case "Đông Nam": {
                switch (huongSource) {
                    case "Đông":
                    case "Nam":
                        return 0.5;
                    case "Đông Nam": return 1;
                    default: return 0;
                }
            }
            case "Tây Nam": {
                switch (huongSource) {
                    case "Tây":
                    case "Nam":
                        return 0.5;
                    case "Tây Nam": return 1;
                    default: return 0;
                }
            }
            case "Tây Bắc": {
                switch (huongSource) {
                    case "Tây":
                    case "Bắc":
                        return 0.5;
                    case "Tây Bắc": return 1;
                    default: return 0;
                }
            }
        }
        return 0;
    }
}
