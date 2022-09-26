package jjfactory.simpleapi.global.ex;


import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400,  "올바르지 않은 형식입니다."),
    METHOD_NOT_ALLOWED(405,  "지원하지 않는 메소드입니다."),
    ENTITY_NOT_FOUND(400, " 해당 엔티티를 찾을 수가 없습니다."),
    INTERNAL_SERVER_ERROR(500, "알 수 없는 에러 (서버 에러)"),
    INVALID_TYPE_VALUE(400, "타입이 올바르지 않습니다."),
    INVALID_TYPE_VALUE2(400, "하이픈이나 문자를 포함할 수 없습니다." ),
    INVALID_LENGTH_VALUE(400, "유효하지 않은 길이입니다." ),
    DUPLICATE_MEMBER(400,  "중복된 회원입니다."),
    ALREADY_REQUESTED(400 , "이미 가입신청이 진행중입니다."),
    NOT_ENDORSED_USER(400 , "기명이 완료된 사용자가 아닙니다"),
    INVALID_REQUEST(400, "현재 접근할 수 없는 상태입니다." ),
    INVALID_CANCEL_REQUEST(400, "기명 취소 요청한 이용자가 아닙니다." ),
    HANDLE_ACCESS_DENIED(403,  "권한이 없습니다."),
    PASSWORD_NOT_MATCH(403,  "비밀번호가 올바르지 않습니다"),
    HANDLE_INVALID_TOKEN(401,  "토큰이 없거나 올바르지 않습니다."),

    DRIVER_ERROR(509,"보험이 유효하지 않습니다."),
    NOT_FOUND_USER(509,  "존재하지 않는 회원입니다."),
    INCORRECT_APPLICATION_NUMBER(509," 보험 청약번호(혹은 설계번호) 중 일부/전체가 올바르지 않습니다."),
    UNDERWRITING_NEEDED(509,"언더라이팅을 아직 진행하지 않았거나, 다시 진행해야 합니다."),
    REJECTED_NO_MODEL(509,"이륜차 가입가능 차종이 아닙니다."),
    REJECTED_NO_USE(509,"이륜차 가입가능 운행요도 아닙니다.");

    private final int status;
    private final String message;


   ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
