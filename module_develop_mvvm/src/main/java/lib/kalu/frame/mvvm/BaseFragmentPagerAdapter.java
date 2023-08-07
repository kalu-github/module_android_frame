package lib.kalu.frame.mvvm;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private final HashMap<Integer, Fragment> mMap = new HashMap<>();

    public BaseFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object o = super.instantiateItem(container, position);
        mMap.remove(position);
        mMap.put(position, (Fragment) o);
        return o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull View container, int position) {
        Object o = super.instantiateItem(container, position);
        mMap.remove(position);
        mMap.put(position, (Fragment) o);
        return o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        mMap.remove(position);
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        mMap.remove(position);
    }

    public final Fragment findFragment(@NonNull int index) {
        try {
            return mMap.get(index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
