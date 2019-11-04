package com.nts.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationResponse;

/**
 * ReservationService 인터페이스를 상속받아 구현한 class
 * @author 황성욱
 * @since 2019-08-09
 */
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationDao rsvnDao;
	
	@Autowired
	private DisplayInfoDao dpInfoDao;
	
	public final int EXIST = 1;
	
	@Override
	public List<ReservationInfo> getList(String reservationEmail) {
		List<ReservationInfo> rsvnInfoList = rsvnDao.selectReservationInfo(reservationEmail);

		for (ReservationInfo rsvnInfo : rsvnInfoList) {
			rsvnInfo.setDisplayInfo(dpInfoDao.selectDisplayInfo(rsvnInfo.getDisplayInfoId()));
			rsvnInfo.setTotalPrice(rsvnDao.selectTotalPrice(rsvnInfo.getReservationInfoId()));
		}
		
		return rsvnInfoList;
	}
	
	@Transactional
	@Override
	public int makeReservation(ReservationParam rsvnParam) throws Exception {
		int rsvnInfoId = rsvnDao.insertOne(rsvnParam);
		rsvnDao.insertPrices(rsvnParam, rsvnInfoId);
		
		return rsvnInfoId;
	}
	
	@Override
	public ReservationResponse getResponse(int reservationInfoId) {
		ReservationResponse rsvnRes = rsvnDao.selectResponse(reservationInfoId);
		rsvnRes.setPrices(rsvnDao.selectPrices(reservationInfoId));
		
		return rsvnRes;
	}
	
	@Override
	public void cancelReservation(int reservationInfoId) throws Exception {
		rsvnDao.updateCancel(reservationInfoId);
	}
	
	@Override
	public boolean isReservationExist(String email) {
		if (rsvnDao.selectReservationExist(email) == EXIST) {
			return true;
		} else {
			return false;
		}
	}
}
