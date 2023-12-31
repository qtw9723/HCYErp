package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import fileServer.HCYFileClient;

public class ManageDocEvt extends MouseAdapter implements ActionListener {
	private ManageDoc md;

	public ManageDocEvt(ManageDoc md) {
		this.md = md;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// 업로드 버튼
		if (e.getSource() == md.getJbtnFileUpload()) {
			new FileUploadDialog(md).setBounds(md.getHcyE().getX() + 300, md.getHcyE().getY() + 150, 565, 500);
		} // if
		
		try {
			// 다운로드 버튼
			if (e.getSource() == md.getJbtnFileDownload()) {
				downloadFile();
			} // if
			
			// 파일 삭제 버튼
			if (e.getSource() == md.getJbtnFileDelete()) {
				deleteFile();
			} // if
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(md, "서버 연결에 문제가 생겼습니다. 기술팀에 문의하세요!","*삐용*오류 발생*삐용*",JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(md, "서버 스트림 연결에 문제가 생겼습니다. 기술팀에 문의하세요!","*삐용*오류 발생*삐용*",JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(md, "데이터 베이스에 문제가 생겼습니다. 기술팀에 문의하세요!","*삐용*오류 발생*삐용*",JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} // catch

		// 참조 버튼
		if (e.getSource() == md.getJbtnRef()) {
			new RefDeptDialog(md);
		} // if
		if (e.getSource() == md.getJbtnLogOut()) {
			logOut();
		} // if

	}// actionPerformed

	private void deleteFile() throws UnknownHostException, IOException, SQLException {
		boolean flag = true;
		StringBuilder msg = new StringBuilder();
		msg.append("다음의 파일을 선택하셨습니다!\n");
		List<Integer> docNoList = new ArrayList<Integer>();
		List<String> fileNameList = new ArrayList<String>();
		for (Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
			if (entry.getValue().isSelected()) {
				flag = false;
				docNoList.add(entry.getKey());
				msg.append(entry.getValue().getText()).append("\n");
				fileNameList.add(entry.getValue().getText());
			} // if
		} // for
		if(flag) {JOptionPane.showMessageDialog(md, "삭제할 파일을 먼저 선택해주세요!"); return;}//if
		msg.append("위의 파일들을 삭제 하시겠습니까?");

		// 삭제 의사 확인
		switch (JOptionPane.showConfirmDialog(md, msg.toString(),"삭제",JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:

			StringBuilder failList = new StringBuilder();
			for (int i = 0; i < docNoList.size(); i++) {
				if (!HCYFileClient.getInstance().deleteFile(fileNameList.get(i))) {
					failList.append(fileNameList.get(i)).append("\n");
				} else {
					ManageDocDAO.getInstance().deleteDoc(docNoList.get(i));
				} // else
			} // for
			if (!failList.toString().isEmpty()) {
				JOptionPane.showMessageDialog(md, failList.toString() + "파일 삭제에 실패했습니다.","삭제",JOptionPane.INFORMATION_MESSAGE);
				return;
			} // if
			JOptionPane.showMessageDialog(md, "파일 삭제를 완료했습니다!","삭제",JOptionPane.INFORMATION_MESSAGE);
			refresh(md.getHcyE().getEvent().getSelectedIndex());
			break;
		default:
		}// switch
	}// deleteFile

	private void downloadFile() throws UnknownHostException, IOException, SQLException {
		boolean flag = true;
		StringBuilder msg = new StringBuilder();
		msg.append("다음의 파일을 선택하셨습니다!\n");
		List<Integer> docNoList = new ArrayList<Integer>();
		List<String> fileNameList = new ArrayList<String>();
		for (Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
			if (entry.getValue().isSelected()) {
				flag = false;
				docNoList.add(entry.getKey());
				msg.append(entry.getValue().getText()).append("\n");
				fileNameList.add(entry.getValue().getText());
			} // if
		} // for
		if(flag) {JOptionPane.showMessageDialog(md, "다운로드할 파일을 먼저 선택해주세요!"); return;}//if
		msg.append("위의 파일들을 다운로드 하시겠습니까?");

		// 삭제 의사 확인
		switch (JOptionPane.showConfirmDialog(md, msg.toString(),"다운로드",JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:

			StringBuilder failList = new StringBuilder();
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("다운로드 받을 디렉토리 선택"); // 다이얼로그 제목 설정
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int choose = fileChooser.showOpenDialog(null);
			String path = "";
			if (choose == JFileChooser.APPROVE_OPTION) {
				// 선택한 파일의 경로를 얻어옵니다.
				path = fileChooser.getSelectedFile().getAbsolutePath();
			} else {
				return;
			} // else
			for (int i = 0; i < docNoList.size(); i++) {
				if (!(HCYFileClient.getInstance().downloadFile(path + File.separator + fileNameList.get(i)))) {
					failList.append(fileNameList.get(i)).append("\n");
				}// if
			} // for
			if (!failList.toString().isEmpty()) {
				JOptionPane.showMessageDialog(md, failList.toString() + "파일 다운로드에 실패했습니다.");
			} // if
			break;
		default:
		}// switch
		JOptionPane.showMessageDialog(md, "다운로드가 성공적으로 완료되었습니다.");
		refresh(md.getHcyE().getEvent().getSelectedIndex());
	}// downloadFile

	public void logOut() {
		md.getHcyE().getTabbedPane().setVisible(false);
		md.getHcyE().addComponent();
		md.getHcyE().setUser(0);
	}// logOut

	public ManageDoc getMd() {
		return md;
	}

	public void refresh(int index) throws SQLException {
		md.getHcyE().getTabbedPane().setVisible(false);
		md.getHcyE().addComponent();
		md.getHcyE().getEvent().login();
		md.getHcyE().getTabbedPane().setSelectedIndex(index);
	}
}// class
