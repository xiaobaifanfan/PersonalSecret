package referenceCase;

public class Case {
	void GetTable::get_first() {
	    bool change = true; //��ʾ���Ķ���һ��������Ҫ���±���
	    bool is_empty; //��ʾ����ʽ�Ҷ�Ϊ�մ�
	    int t; 
	    //ѭ����ֱ��û�иĶ�Ϊֹ����change = false
	    while(change) {
	        change = false;
	        //ѭ��ÿ���ķ�
	        for(auto &g : G) {
	            is_empty = true;
	            t = 0;
	            while(is_empty && t < static_cast<int>(g.right.size())) {
	                is_empty = false;
	                if(!inVT(g.right[t])) {
	                    if(first[g.left].find(g.right[t]) == first[g.left].end()) {
	                        first[g.left].insert(g.right[t]);
	                        change = true;
	                    }
	                    continue;
	                }  
	                for(auto i : first[g.right[t]]) {
	                    if(first[g.left].find(i) == first[g.left].end()) {
	                        first[g.left].insert(i);
	                        change = true;
	                    }
	                }

	                if(first[g.right[t]].find("��") != first[g.right[t]].end()) {
	                    is_empty = true;
	                    t++;
	                }
	            }
	            if(t == static_cast<int>(g.right.size()) && first[g.left].find("��") == first[g.left].end()) {
	                first[g.left].insert("��");
	                change = true;
	            }
	        }
	    }

	    first.erase("S");
	    
	}
}
