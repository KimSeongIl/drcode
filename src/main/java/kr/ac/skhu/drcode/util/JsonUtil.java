package kr.ac.skhu.drcode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
	/**
	 * 데이터 결과를 JSON 성공 규격에 맞게 만들어주는 함수
	 * @param mapSource 결과 데이터
	 * @return JSON규격에 의해 만들어진 결과 데이터
	 */
	public static Map<String, Object> putSuccessJsonContainer(Map<String, Object> mapSource) {
		return makeJsonContainer("success", mapSource);
	}
	
	/**
	 * 결과가 실패일 경우
	 * @param errorCd 오류코드(클래스명+EER+숫자)
	 * @param errorMsg 오류메시지(사용자게에게 보여줄 메시지)
	 * @param currentTime 오류 메시지 발생 시간
	 * @return Map형식의 오류 결과
	 */
	public static Map<String, Object> putFailJsonContainer(String errorCd, int errorCode, String errorMsg, long currentTime) {
		Map<String, Object> mapResData = new HashMap<String, Object>();
		mapResData.put("errorCd", errorCd);
		mapResData.put("errorCode", errorCode);
		mapResData.put("errorMsg", errorMsg);
		mapResData.put("time", currentTime);
		
		return makeJsonContainer("error", mapResData);
	}
	
	/**
	 * 액션코드를 적지 않은 함수 (actcd는 9999기본)
	 * @param errorCd 오류코드 
	 * @param errorMsg 오류메시지
	 * @return
	 */
	public static Map<String, Object> putFailJsonContainer(String errorCd, String errorMsg){
		return putFailJsonContainer(errorCd,9999, errorMsg, System.currentTimeMillis());
	}
	
	/**
	 * JSON 표준 컨테이너에 넣어준다. 
	 * @param result 결과 메시지
	 * @param mapSource 원소스
	 * @return 만들어진 Map형식
	 */
	private static Map<String, Object> makeJsonContainer(String result, Map<String, Object> mapSource) {
		@SuppressWarnings("rawtypes")
		ArrayList<Map> arrayRsltData = new ArrayList<Map>();
		Map<String, Object> mapResData = new HashMap<String, Object>();
		arrayRsltData.add(mapSource);
		mapResData.put("result", result);
		mapResData.put("resData", arrayRsltData);
		return mapResData;
	}

	

}