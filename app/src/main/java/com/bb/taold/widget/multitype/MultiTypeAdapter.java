package com.bb.taold.widget.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.bb.taold.widget.multitype.data.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 引用github
 * Created by fancl.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements FlatTypeAdapter, TypePool {


    protected List<? super Item> items;

    private LayoutInflater inflater;

    private TypePool delegate;




    public MultiTypeAdapter() {
        this.items = new ArrayList<>();
        this.delegate = new MultiTypePool();

    }

    public MultiTypeAdapter(@NonNull List<? super Item> items) {
        this.items = items;
        this.delegate = new MultiTypePool();

    }

    public MultiTypeAdapter(@NonNull List<? super Item> items, TypePool delegate) {
        this.items = items;
        this.delegate = delegate;

    }


    public List<? super Item> getItems() {
        return items;
    }


    public void setItems(List<? super Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<? extends Item> items) {

        this.items.addAll((Collection) items);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return getProviderByIndex(viewType).onCreateViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = (Item) items.get(position);
        getProviderByClass(onFlattenClass(item)).getCurrentPositon(position);
        getProviderByClass(onFlattenClass(item)).onBindViewHolder(holder, item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public Class onFlattenClass(@NonNull Item item) {
        return item.getClass();
    }

    @NonNull
    @Override
    public Item onFlattenItem(@NonNull Item item) {
        return item;
    }

    @Override
    public void register(@NonNull Class<? extends Item> clazz, @NonNull ItemViewProvider provider) {
        delegate.register(clazz, provider);
    }

    @Override
    public int indexOf(@NonNull Class<? extends Item> clazz) throws ProviderNotFoundException {
        int index = delegate.indexOf(clazz);
        if (index >= 0)
            return index;

        throw new ProviderNotFoundException(clazz);
    }

    @NonNull
    @Override
    public ArrayList<Class<? extends Item>> getContents() {
        return delegate.getContents();
    }

    @NonNull
    @Override
    public ArrayList<ItemViewProvider> getProviders() {
        return delegate.getProviders();
    }

    @NonNull
    @Override
    public ItemViewProvider getProviderByIndex(int index) {
        return delegate.getProviderByIndex(index);
    }

    @NonNull
    @Override
    public <T extends ItemViewProvider> T getProviderByClass(@NonNull Class<? extends Item> clazz) {
        return delegate.getProviderByClass(clazz);
    }


    @Override
    public int getItemViewType(int position) {
        Item item = (Item) items.get(position);
        return indexOf(onFlattenClass(item));
    }

    public void registerAll(@NonNull final MultiTypePool pool) {
        for (int i = 0; i < pool.getContents().size(); i++) {
            delegate.register(pool.getContents().get(i), pool.getProviders().get(i));
        }
    }

    public void applyGlobalMultiTypePool() {

        for (int i = 0; i < GlobalMultiTypePool.getContents().size(); i++) {
            final Class<? extends Item> clazz = GlobalMultiTypePool.getContents().get(i);
            final ItemViewProvider provider = GlobalMultiTypePool.getProviderByClass(clazz);
            if (!this.getContents().contains(clazz)) {
                this.register(clazz, provider);
            }
        }
    }


}
