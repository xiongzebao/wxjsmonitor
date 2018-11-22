package cn.framework.commenlibrary.model;

public class PageObj<T> {
    private PageInfo<T> page;

    public PageInfo<T> getPage() {
        return page;
    }

    public void setPage(PageInfo<T> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("curPage:"+page.getCurPage());
        sb.append("nextPage:"+page.getNextPage());
        sb.append("totalPage:"+page.getTotalPage());
        sb.append("totalSize:"+page.getTotalSize());
        sb.append(page.getDataList().toString());
        return sb.toString();
    }
}
