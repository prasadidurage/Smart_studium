package lk.ijse.b72.finalproject.back_end.util;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KUtil {


        private int status; // HTTP status code
        private String message; // Message to be returned
        private Object data; // Data to be returned

        // Constructor
        public KUtil(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        // Getters and Setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

