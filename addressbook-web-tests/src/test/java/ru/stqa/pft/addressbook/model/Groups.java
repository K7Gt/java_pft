package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Golem on 07.04.2017.
 */
public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }


    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups withOut(GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public Groups withModified (int id,GroupData group){
        Groups groups = new Groups(this);
        for(GroupData gr:groups){
            if(gr.getId() == id){
                groups.remove(gr);
                break;
            }
        }
        groups.add(group);
        return groups;

    }
}
