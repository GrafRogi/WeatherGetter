package weather.getter.model;

public class MessageRequest {

  private String phoneNumber;

  private String message;

  public MessageRequest() {
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "MessageRequest{" +
        "phoneNumber='" + phoneNumber + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
