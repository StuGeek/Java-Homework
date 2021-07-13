import java.util.ArrayList;
import java.util.List;

//用户
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

	//别人关注该用户后，用户会收到一条信息提示，这条信息提示存在用户的信息列表里
	public void notify(String message) {
		messageList.add(message);
	}

	//将用户关注的每一个人都打印出来
	public void showFollowList() {
		System.out.println(name + "'s follow list:");
		for (User follower : followList) {
			System.out.println(follower.getName());
		}
	}

	//将用户的每一个粉丝都打印出来
	public void showFansList() {
		System.out.println(name + "'s fans list:");
		for (User fan: fansList) {
			System.out.println(fan.getName());
		}
	}

	//将用户收到的每一条信息都打印出来
	public void ShowMessageList() {
		System.out.println(name + "'s message list:");
		for (String message : messageList) {
			System.out.println(message);
		}
	}
}

//关注按钮，在被关注的人的界面上
class FollowButton {
	private User pageUser;
	private List<Observer> observerList = new ArrayList<>();

	public FollowButton(User pageUser) {
		this.pageUser = pageUser;
	}

	//点击关注按钮后，实现关注的人和被关注的人关注列表和粉丝列表的更新，被关注的人收到一条被关注的信息
	public void click(User clicker) {
		for (Observer observer : observerList) {
			observer.notify(pageUser, clicker);
		}
	}

	public void addObserver(Observer observer) {
		observerList.add(observer);
	}
}

//观察者接口
interface Observer {
	void notify(User pageUser, User follower);
}

//关注的人关注列表更新
class FollowIncrease implements Observer {
	public void notify(User pageUser, User follower) {
		follower.getFollowList().add(pageUser);
	}
}

//被关注的人粉丝列表更新
class FansIncrease implements Observer {
	public void notify(User pageUser, User follower) {
		pageUser.getFansList().add(follower);
	}
}

//被关注的人收到一条被关注的信息
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