/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.todivefor.swing7.model.Message;

/**
 *
 * @author peterream
 * 
 * This is a sort of simulated message server
 */
public class MessageServer implements Iterable<Message> {
    
    private final Map<Integer, List<Message>> messages;
    
    private final List<Message> selected;

    public MessageServer() {
        
        messages = new TreeMap<>();
        
        selected = new ArrayList<>();
        
        List<Message> list = new ArrayList<>();
        list.add(new Message("The cat is missing", 
                "Have you seen Felix anywhere?"));
        list.add(new Message("See you later?", 
                "Are we still meeting in the pub?"));
        messages.put(0, list);
        
        list = new ArrayList<>();
        list.add(new Message("How about dinner later?", 
                "Are you doing anything later on?"));
        messages.put(1, list);
    }
    
    public void setSelectedServers(Set<Integer> servers) {
        
        selected.clear();
                
        for (Integer id : servers) {
            if (messages.containsKey(id)) {
                selected.addAll(messages.get(id));
            }
        }
    }
    
    public int getMessageCount() {
        
        return selected.size();
    }

    @Override
    public Iterator<Message> iterator() {
        
        return new MessageIterator(selected);
    }
}

class MessageIterator implements Iterator {
	
	private Iterator<Message> iterator;
	
	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Object next() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
	
}
