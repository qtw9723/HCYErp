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

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import VO.DocVO;
import fileServer.HCYFileClient;
import login.HCYErp;

public class FileUploadDialogEvt extends MouseAdapter implements ActionListener {
	private FileUploadDialog fud;
	private  List<String> selectPathList ;
	public FileUploadDialogEvt(FileUploadDialog fud) {
		this.fud = fud;
		
		fud.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				fud.dispose();
			}//windowClosing
			
		});//addWindowListener
	}//constructor

	public void addFile() {
	    JFileChooser jfc = new JFileChooser();
	    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY); // 파일만 선택하도록 설정

	    int value = jfc.showOpenDialog(fud);
	    
	    //리스트 없다면 객체 생성
	    if(selectPathList==null) {
	    	selectPathList = new ArrayList<String>();
	    }//if

	    if (value == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = jfc.getSelectedFile();

	        if (selectedFile.isFile()) { // 선택한 것이 파일인 경우에만 처리
	        	String selectPath = selectedFile.getAbsolutePath();
	        	if(exist(selectPath)) {//중복 파일이 없으면 실행
	        		selectPathList.add(selectPath);
	        		fud.getListmodel().addElement(selectPath.substring(selectPath.lastIndexOf("\\")+1));
	        	}else {
	        		JOptionPane.showMessageDialog(jfc, "이미 존재하는 파일입니다");
	        	}//else
	        }else {
	        	JOptionPane.showMessageDialog(jfc, "파일을 업로드 해주세요");
	        }//else
	    }//if
	}//addFile
	
	private boolean exist(String filePath) {
		for(int i =0;i<selectPathList.size();i++) {
			String existPath=selectPathList.get(i);
			if(filePath.equals(existPath)) {
				return false;
			}//if
		}//for
		return true;//중복 파일 없음
		
	}//exist
	
	public void deleteFile() {
		int selectedFile=fud.getJlFile().getSelectedIndex();
		
		if(selectedFile>=0) {
			selectPathList.remove(selectedFile);
			fud.getListmodel().remove(selectedFile);
		}//if
	}//deleteFile
	
	public void uploadFile() throws UnknownHostException, IOException, SQLException {
		for(String filePath:selectPathList) {
			HCYFileClient.getInstance().uploadFile(new File(filePath));
			DocVO dVO = null;
				dVO = new DocVO();
				dVO.setDocName(filePath.substring(filePath.lastIndexOf("\\")+1));
				dVO.setDeptNo(fud.getMde().getMd().getHcyE().getUser());
				
			ManageDocDAO.getInstance().insertDoc(dVO);
			JPanel jpdoc = fud.getMde().getMd().getJpDoc();
			JCheckBox checkBox = new JCheckBox(filePath.substring(filePath.lastIndexOf("\\")+1));
			fud.getMde().getMd().getCheckBoxList().add(checkBox);
			jpdoc.add(checkBox);
			jpdoc.setLayout(new BoxLayout(jpdoc, BoxLayout.Y_AXIS));
			fud.repaint();
		}//for
		JOptionPane.showMessageDialog(fud, "파일 업로드를 성공적으로 종료했습니다.");
	}//uploadFile
	
	
	
	public List<String> getSelectPath() {
		return selectPathList;
	}

	public void cancelFileUpload() {
		fud.dispose();
	}//cancelFileUpload
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fud.getJbtnCancel()) {
			cancelFileUpload();
		}
		if(e.getSource()==fud.getJbtnAddFile()) {
			addFile();
		}
		if(e.getSource()==fud.getJbtnDeleteFile()) {
			deleteFile();
		}//if
		
		//
		if(e.getSource()==fud.getJbtnUpload()) {
			try {
				uploadFile();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(fud, "서버와의 연결이 취소되었습니다.");
			}catch (ConnectException ce){
				ce.printStackTrace();
				JOptionPane.showMessageDialog(fud, "서버를 찾을 수 없습니다.");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(fud, "파일을 올리는데 문제가 발생했습니다.");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(fud, "데이터 베이스에서 문제가 발생했습니다.\n파일 전송은 성공적으로 마무리했습니다.");
				e1.printStackTrace();
			} 
		}//if
	}//actionPerformed

}//class
