package jjfactory.freelancer.common.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(T data) {
        this.status = HttpStatus.OK;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

}
