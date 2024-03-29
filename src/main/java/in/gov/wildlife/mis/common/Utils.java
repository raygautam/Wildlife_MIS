package in.gov.wildlife.mis.common;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static <T> PaginationMetaDTO createPaginationMeta(Page<T> page) {
        PaginationMetaDTO paginationMeta = new PaginationMetaDTO();
        paginationMeta.setTotalCount(page.getTotalElements());
        paginationMeta.setTotalPages(page.getTotalPages());
        paginationMeta.setPageSize(page.getSize());
        paginationMeta.setPageNumber(page.getNumber() + 1);
        paginationMeta.setIsFirst(page.isFirst());
        paginationMeta.setIsLast(page.isLast());

        return paginationMeta;
    }
}
