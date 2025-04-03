package org.example.btvnbuoi4.common;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PostPageRequest implements Pageable {

    Integer limit;
    Integer offset;
    private Sort sort;

    public PostPageRequest(Integer limit, Integer offset, Sort sort) {
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public PostPageRequest(Integer limit, Integer offset) {
        this(limit, offset, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return offset/limit+1;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PostPageRequest(getPageSize(),(int) (getOffset() + getPageSize()));
    }

    public Pageable previous() {
        return hasPrevious() ? new PostPageRequest(getPageSize(), (int) (getOffset() - getPageSize())) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new PostPageRequest(getPageSize(), 0);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PostPageRequest(getPageSize(), pageNumber * getPageSize())   ;
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    public static PostPageRequest of(int page, int size) {
        return new PostPageRequest(size, page * size);
    }

    public static PostPageRequest of(int page, int size, Sort sort) {
        return new PostPageRequest(size, page * size, sort);
    }

}
