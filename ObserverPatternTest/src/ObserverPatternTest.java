import java.util.ArrayList;
import java.util.List;

//�û�
class User {
	private String name;
	private List<User> followList = new ArrayList<>();
	private List<User> fansList = new ArrayList<>();
	private List<String> messageList = new ArrayList<>();

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<User> getFollowList() {
		return followList;
	}

	public List<User> getFansList() {
		return fansList;
	}

	//���˹�ע���û����û����յ�һ����Ϣ��ʾ��������Ϣ��ʾ�����û�����Ϣ�б���
	public void notify(String message) {
		messageList.add(message);
	}

	//���û���ע��ÿһ���˶���ӡ����
	public void showFollowList() {
		System.out.println(name + "'s follow list:");
		for (User follower : followList) {
			System.out.println(follower.getName());
		}
	}

	//���û���ÿһ����˿����ӡ����
	public void showFansList() {
		System.out.println(name + "'s fans list:");
		for (User fan: fansList) {
			System.out.println(fan.getName());
		}
	}

	//���û��յ���ÿһ����Ϣ����ӡ����
	public void ShowMessageList() {
		System.out.println(name + "'s message list:");
		for (String message : messageList) {
			System.out.println(message);
		}
	}
}

//��ע��ť���ڱ���ע���˵Ľ�����
class FollowButton {
	private User pageUser;
	private List<Observer> observerList = new ArrayList<>();

	public FollowButton(User pageUser) {
		this.pageUser = pageUser;
	}

	//�����ע��ť��ʵ�ֹ�ע���˺ͱ���ע���˹�ע�б�ͷ�˿�б�ĸ��£�����ע�����յ�һ������ע����Ϣ
	public void click(User clicker) {
		for (Observer observer : observerList) {
			observer.notify(pageUser, clicker);
		}
	}

	public void addObserver(Observer observer) {
		observerList.add(observer);
	}
}

//�۲��߽ӿ�
interface Observer {
	void notify(User pageUser, User follower);
}

//��ע���˹�ע�б����
class FollowIncrease implements Observer {
	public void notify(User pageUser, User follower) {
		follower.getFollowList().add(pageUser);
	}
}

//����ע���˷�˿�б����
class FansIncrease implements Observer {
	public void notify(User pageUser, User follower) {
		pageUser.getFansList().add(follower);
	}
}

//����ע�����յ�һ������ע����Ϣ
class BeFollowed implements Observer {
	public void notify(User pageUser, User follower) {
		String notify_message = "You are followed by " + follower.getName();
		pageUser.notify(notify_message);
	}
}

public class ObserverPatternTest {
	public static void main(String[] args) {
		User zhangSan = new User("ZhangSan");
		User liSi = new User("LiSi");
		User wangWu = new User("WangWu");
		User zhaoLiu = new User("zhaoliu");
		FollowButton[] followButtons = {new FollowButton(zhangSan), new FollowButton(liSi), 
					new FollowButton(wangWu), new FollowButton(zhaoLiu)};
		FollowIncrease followIncrease = new FollowIncrease();
		FansIncrease fansIncrease = new FansIncrease();
		BeFollowed beFollowed = new BeFollowed();
		for (FollowButton followButton : followButtons) {
			followButton.addObserver(followIncrease);
			followButton.addObserver(fansIncrease);
			followButton.addObserver(beFollowed);
		}
		followButtons[0].click(liSi);
		followButtons[0].click(wangWu);
		followButtons[0].click(zhaoLiu);
		followButtons[1].click(zhangSan);
		followButtons[1].click(wangWu);
		followButtons[2].click(zhangSan);
		followButtons[2].click(liSi);
		followButtons[2].click(zhaoLiu);
		followButtons[3].click(liSi);
		followButtons[3].click(wangWu);
		zhangSan.showFansList();
		zhangSan.showFollowList();
		zhangSan.ShowMessageList();
		liSi.showFansList();
		liSi.showFollowList();
		liSi.ShowMessageList();
		wangWu.showFansList();
		wangWu.showFollowList();
		wangWu.ShowMessageList();
		zhaoLiu.showFansList();
		zhaoLiu.showFollowList();
		zhaoLiu.ShowMessageList();
	}
}