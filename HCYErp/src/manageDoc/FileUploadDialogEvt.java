package manageDoc;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import VO.DocVO;
import fileServer.HCYFileClient;

public class FileUploadDialogEvt extends MouseAdapter implements ActionListener {
	private List<String> selectPathList;
	private FileUploadDialog fud;

	public FileUploadDialogEvt(FileUploadDialog fud) {
		this.fud = fud;
		fud.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				fud.dispose();
			}// windowClosing
		});
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// 취소 버튼
		if (e.getSource() == fud.getJbtnCancel()) {
			cancelFileUpload();
		} // if

		// 파일 추가 버튼
		if (e.getSource() == fud.getJbtnAddFile()) {
			addFile();
		} // if

		// 삭제 버튼
		if (e.getSource() == fud.getJbtnDeleteFile()) {
			deleteFile();
		} // if

		// 업로드버튼
		if (e.getSource() == fud.getJbtnUpload()) {
			try {
				uploadFile();
			} catch (UnknownHostException uhe) {
				uhe.printStackTrace();
				JOptionPane.showMessageDialog(fud, "서버와의 연결이 취소되었습니다.");
			} catch (ConnectException ce) {
				ce.printStackTrace();
				JOptionPane.showMessageDialog(fud, "서버를 찾을 수 없습니다.");
			} catch (IOException ioe) {
				ioe.printStackTrace();
				JOptionPane.showMessageDialog(fud, "파일을 올리는데 문제가 발생했습니다.");
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(fud, "데이터 베이스에서 문제가 발생했습니다.\n파일 전송은 성공적으로 마무리했습니다.");
				sqle.printStackTrace();
			} // catch
		} // if
	}// actionPerformed

	public void addFile() {
		// 업로드할 파일 선택
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int value = jfc.showOpenDialog(fud);
		// 리스트 없다면 객체 생성
		if (selectPathList == null) {
			selectPathList = new ArrayList<String>();
		} // if
			// 파일 선택시 예외처리
		if (value == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			// 파일인 경우
			if (selectedFile.isFile()) {
				String selectPath = selectedFile.getAbsolutePath();
				// 중복 파일 확인
				if (exist(selectPath)) {
					selectPathList.add(selectPath);
					fud.getListmodel().addElement(selectPath.substring(selectPath.lastIndexOf("\\") + 1));
				} else {
					JOptionPane.showMessageDialog(jfc, "이미 존재하는 파일입니다");
				} // else
			} else {
				JOptionPane.showMessageDialog(jfc, "파일을 업로드 해주세요");
			} // else
		} // if
	}// addFile

	// 중복이 없는 경우 true
	private boolean exist(String filePath) {
		for (String existPath : selectPathList) {
			// 기존 파일과 중복 검사
			if (filePath.equals(existPath)) {
				return false;
			} // if
		} // for
		return true;
	}// exist

	public void deleteFile() {
		int selectedFile = fud.getJlFile().getSelectedIndex();
		// 선택 파일이 있는경우
		if (selectedFile >= 0) {
			selectPathList.remove(selectedFile);
			fud.getListmodel().remove(selectedFile);
			// 없는 경우
		} else {
			JOptionPane.showMessageDialog(fud, "삭제하실 파일을 선택해주세요!");
		} // else
	}// deleteFile

	public void uploadFile() throws UnknownHostException, IOException, SQLException {
		String docName = "";
		String docPath = "";
		List<String> dvDocNameList = null;
		ManageDocDAO mdDAO = null;
		// 추가된 파일 하나씩 for문
		for (String filePath : selectPathList) {
			mdDAO = ManageDocDAO.getInstance();
			// 업로드된 문서목록 받기
			dvDocNameList = mdDAO.selectDoc();
			// 문서 이름, 경로 저장
			docName = filePath.substring(filePath.lastIndexOf("\\") + 1);
			docPath = filePath.substring(0, filePath.lastIndexOf("\\"));
			// db에 있는 파일과 겹치는 지 확인
			for (String dbDocName : dvDocNameList) {
				if (docName.equals(dbDocName)) {
					fud.getListmodel().removeElement(docName);
					docName = JOptionPane.showInputDialog("선택하신 파일과 같은 이름의 파일이 이미 업로드 되어있습니다. 새로운 이름을 입력해주세요!",
							docName.substring(0, docName.lastIndexOf(".")) + "_최종"
									+ docName.substring(docName.lastIndexOf(".")));
					if (docName != null) {
						File file = new File(filePath);
						if (file.renameTo(new File(docPath + File.separator + docName))) {
							selectPathList.remove(filePath);
							filePath = docPath + File.separator + docName;
							fud.getListmodel().addElement(docName);
							selectPathList.add(filePath);
							JOptionPane.showMessageDialog(fud, "파일 이름을 "+docName+"으로 바꿨습니다! 업로드를 다시 시도해주세요!");
							return;
						} // if
						JOptionPane.showMessageDialog(fud, "파일 이름 바꾸기를 실패했습니다. 다시 시도해주세요!");
						return;
					} // if
					return;
				} // if
			} // for
			HCYFileClient.getInstance().uploadFile(new File(filePath));
			DocVO dVO = null;
			dVO = new DocVO();
			dVO.setDocName(docName);
			dVO.setDeptNo(fud.getMd().getHcyE().getUser());
			mdDAO.insertDoc(dVO);
			fud.getMd().getJpDoc().add(new Checkbox(docName));
			fud.repaint();
		} // for
		JOptionPane.showMessageDialog(fud, "파일 업로드를 성공적으로 종료했습니다.");
	}// uploadFile

	public List<String> getSelectPath() {
		return selectPathList;
	}// getSelectPath

	public void cancelFileUpload() {
		fud.dispose();
	}// cancelFileUpload

}// class
