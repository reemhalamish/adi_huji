package filters.filters;

import exceptions.FilterException;
import filesprocessing.FileInfo;

/**
 * Created by adi on 25/05/17.
 */
public class HiddenFilter implements Filter{

    private boolean isHidden;

    public HiddenFilter(String isHidden) throws FilterException {
        if (isHidden.equals("NOT")){
            this.isHidden = false;
        }
        if (isHidden.equals("YES")){
            this.isHidden = true;
        }
        else {throw new FilterException(); }
    }


    @Override
    public boolean filter(FileInfo toFilter) {
        return toFilter.hidden == isHidden;
    }
}
