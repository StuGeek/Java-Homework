import java.util.ArrayList;
import java.util.List;

//�����߽ӿ�
interface Receiver {
	void receive(Sender sender, String message);
}

//�����߽ӿ�
interface Sender {
	void send(Receiver receiver, String message);
}

//�û����ȿ����Ƿ����ߣ�Ҳ�����ǽ�����
class User implements Sender, Receiver {
	private String name;
	private List<String> messageList = new ArrayList<>();

	public User(String name) {
		this.name = name;
	}

	//����Ϣ����һ���ĸ�ʽ����
	public void send(Receiver receiver, String message) {
		String send_message = "[From " + name + "]" + message;
		receiver.receive(this, send_message);
	}

	//���յ���Ϣ�󣬴����û�����Ϣ�б���
	public void receive(Sender sender, String message) {
		messageList.add(message);
	}

	//���û���Ϣ�б��е�ÿһ����Ϣ��ӡ����
	public void showMessages() {
		for (String message : messageList) {
			System.out.println(message);
		}
	}
}

//Ƶ���������û����͵���Ϣ
class Channel implements Receiver {
	private String name;
	private List<User> userList = new ArrayList<>();

	public Channel(String name) {
		this.name = name;
	}

	//Ƶ�����յ���Ϣ�󣬰���һ����ʽ����Ϣ���͸������Ƶ���ڴ��ڵ������û�
	public void receive(Sender sender, String message) {
		String send_message = "[From " + name + "]" + message;
		for (User user : userList) {
			if (user == sender) continue;
			user.receive(sender, send_message);
		}
	}

	//�����û�
	public void add(User user) {
		userList.add(user);
	}

	//ɾ���û�
	public void remove(User user) {
		userList.remove(user);
	}
}

public class SenderAndReceiverTest {
	public static void main(String[] args) {
		User zhangSan = new User("ZhangSan");
		User liSi = new User("LiSi");
		User wangWu = new User("WangWu");
		User zhaoLiu = new User("zhaoLiu");
		Channel douBiFanZhiJiDi = new Channel("douBiFanZhiJiDi");
		douBiFanZhiJiDi.add(zhangSan);
		douBiFanZhiJiDi.add(liSi);
		douBiFanZhiJiDi.add(wangWu);
		Channel yongYuanDe308 = new Channel("yongYuanDe308");
		yongYuanDe308.add(liSi);
		yongYuanDe308.add(wangWu);
		yongYuanDe308.add(zhaoLiu);
		zhaoLiu.send(yongYuanDe308, "What did you guys do during the holidays");
		wangWu.send(yongYuanDe308, "I went to eat barbecue, haha");
		zhaoLiu.send(yongYuanDe308, "It sounds interesting, we can barbecue together next time!");
		yongYuanDe308.remove(zhaoLiu);
		zhangSan.send(douBiFanZhiJiDi, "Please help me ask what Zhao Liu likes");
		wangWu.send(douBiFanZhiJiDi, "Zhang, I was Wang. Can't help you, bro. sorry");
		zhangSan.send(douBiFanZhiJiDi, "Lisi, would you mind...");
		liSi.send(douBiFanZhiJiDi, "You know I don��t like to be a matchmaker.");
		System.out.println("ZhangSan's messages list:");
		zhangSan.showMessages();
		System.out.println("\nLiSi's messages list:");
		liSi.showMessages();
		System.out.println("\nWangWu's messages list:");
		wangWu.showMessages();
		System.out.println("\nZhaoLiu's messages list:");
		zhaoLiu.showMessages();
	}
}