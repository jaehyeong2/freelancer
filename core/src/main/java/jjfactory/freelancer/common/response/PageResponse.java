package jjfactory.freelancer.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T>{
    public List<T> data;
    public long currentIndex;
    public long totalElement;
    public long currentPage;
    public long totalPage;

    public PageResponse(Page<T> data, Pageable page) {
        setData(data, page);
    }

    public void setData(Page<T> data, Pageable page) {
        setData(data.getContent(), data.getNumber(), data.getTotalElements(), page);
    }

    public void setData(List<T> data, long currentIndex, long totalElements, Pageable page) {
        this.data = data;
        this.currentIndex = currentIndex;
        this.currentPage = page.getPageNumber();
        this.totalElement = totalElements;
        this.totalPage = totalElements / page.getPageSize();
        if(totalElements % page.getPageSize() > 0) totalPage++;
    }
}
