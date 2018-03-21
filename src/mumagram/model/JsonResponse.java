package mumagram.model;

public class JsonResponse {
	// 1000 - success, 2000 - error, 3000 - access
	private int code;
	private String status;
	private Object data;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
