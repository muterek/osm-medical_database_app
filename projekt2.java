package projekt2MR;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class projekt2 extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataBase BazaDanych;
	private String imie, nazwisko, pesel, waga, bmi;
	Date data;
	private long peseldb, data2 ;
	float suma, srednia, min, maks;
	
	JPanel badanie, danePacjenta, listaPacjentow, listaPacjentow1, listaPacjentow2, wykresBadan, daneWybrPacj, 
			listaBadanPacjenta, listaBadanPacjenta1, listaBadanPacjenta2;
	JLabel limie, lnazwisko, lpesel, ldata, lwaga, lbmi, lmin, lmax, lsrednia, lmin2, lmax2, lsrednia2,
			limiepacjenta, lnazwiskopacjenta, lpeselpacjenta, limiepacjenta2, lnazwiskopacjenta2, lpeselpacjenta2, llista;
	JTextField timie, tnazwisko;
	JNumberTextField tpesel,tdata, twaga, tbmi;
	JButton bzapisz, banuluj, bbadanieZapisz, bbadanieAnuluj, bdodaj, busun, busunbadanie;
	public JTable tTabela, tTabelaBad;
	ChartPanel cp;
	JComboBox <String> cwykres;
	JDateChooser kalendarz;
	Calendar rightNow;
	DefaultCategoryDataset ds = new DefaultCategoryDataset( );
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String dataFormat;
	public static String[] opcjeWykres = {" ", "Waga", "BMI"};
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gbcdanePacjenta = new GridBagConstraints();
	GridBagConstraints gbcbadanie = new GridBagConstraints();
	GridBagConstraints gbcwykresBadan = new GridBagConstraints();
	GridBagConstraints gbcdaneWybrPacj = new GridBagConstraints();
	DefaultTableModel modelTabeli, modelTabeliBad;
	
	
	public void pokazPacjenta() {								//wyœwietlanie pacjentów w tabeli okna g³ównego
    	ArrayList<pacjent> lista = BazaDanych.pacjentLista();
    	//listaPacjentow1.add(tTabela);
    	modelTabeli = (DefaultTableModel)tTabela.getModel();
    	Object[] kol = new Object[3];
    	for(int i=0 ; i<lista.size(); i++) {
    		kol[0] = lista.get(i).getImie();
    		kol[1] = lista.get(i).getNazwisko();
    		kol[2] = lista.get(i).getPesel();
    		modelTabeli.addRow(kol);
    	}
    }
	
	public void pokazBadanie(long Pesel) {								// wyœwietlanie listy badañ danego pacjenta
		ArrayList<badanie> badanie = BazaDanych.pacjentBadanie(Pesel);
		modelTabeliBad = (DefaultTableModel)tTabelaBad.getModel();
		Object[] kol = new Object[3];
    	for(int i=0 ; i<badanie.size(); i++) {
    		kol[0] = badanie.get(i).getData();
    		kol[1] = badanie.get(i).getWaga();
    		kol[2] = badanie.get(i).getBMI();
//    		modelTabeli.addRow(kol);
    	}
	}
	
	public projekt2() {

		BazaDanych = new DataBase();
		
		setLayout(new GridBagLayout()); 
		setTitle("Aplikacja wspomagaj¹ca pracê dietetyka"); 
		setBackground(Color.white); 

		gbc.insets = new Insets(5,5,5,5); 
		
		//------------------------------------ DANE PACJENTA------------------------------------------------
		
		danePacjenta = new JPanel(); 
		danePacjenta.setBorder(BorderFactory.createTitledBorder("Dane Pacjenta"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		danePacjenta.setBackground(Color.white);
		add(danePacjenta, gbc);
				
		danePacjenta.setLayout(new GridBagLayout()); 
		gbcdanePacjenta.insets = new Insets(5,5,5,5); 
		
		limie = new JLabel("Imiê: "); 
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 0;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(limie, gbcdanePacjenta);
		
		timie = new JTextField(""); 
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 0;
		gbcdanePacjenta.gridwidth = 2;
		timie.setPreferredSize(new Dimension(165, 20));
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(timie, gbcdanePacjenta);
		
		lnazwisko = new JLabel("Nazwisko: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 1;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lnazwisko, gbcdanePacjenta);
		
		tnazwisko = new JTextField("");
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 1;
		gbcdanePacjenta.gridwidth = 2;
		tnazwisko.setPreferredSize(new Dimension(165, 20));
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(tnazwisko, gbcdanePacjenta);
		
		lpesel = new JLabel("PESEL: ");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 2;
		gbcdanePacjenta.gridwidth = 2;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(lpesel, gbcdanePacjenta);
		
		tpesel = new JNumberTextField();
		gbcdanePacjenta.gridx = 2;
		gbcdanePacjenta.gridy = 2;
		gbcdanePacjenta.gridwidth = 2;
		tpesel.setPreferredSize(new Dimension(165, 20));
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(tpesel, gbcdanePacjenta);
		tpesel.addKeyListener(new KeyAdapter() {			//d³ugoœæ numeru PESEL nie wiêksza ni¿ 11 cyfr
		    public void keyTyped(KeyEvent e) { 
		        if (tpesel.getText().length()>= 11) 
		            e.consume(); 
		    }  
		});
		
		bzapisz = new JButton("Zapisz");
		gbcdanePacjenta.gridx = 0;
		gbcdanePacjenta.gridy = 5;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(bzapisz, gbcdanePacjenta);
		
		banuluj = new JButton("Anuluj");
		gbcdanePacjenta.gridx = 1;
		gbcdanePacjenta.gridy = 5;
		gbcdanePacjenta.gridwidth = 1;
		gbcdanePacjenta.fill = GridBagConstraints.HORIZONTAL;
		danePacjenta.add(banuluj, gbcdanePacjenta);
		
		bzapisz.addActionListener(this);
		banuluj.addActionListener(this);
		
		//-----------------------------BADANIE----------------------------------------
		
		badanie = new JPanel(); 
		badanie.setBorder(BorderFactory.createTitledBorder("Badanie"));
		gbc.gridx = 0;
		gbc.gridy = 1;
		badanie.setBackground(Color.white);
		this.add(badanie, gbc);
		badanie.setLayout(new GridBagLayout());
		gbcbadanie.insets = new Insets(5,5,5,5);
		
		ldata = new JLabel("Data: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 0;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(ldata, gbcbadanie);
		
		kalendarz = new JDateChooser();
		gbcbadanie.gridx = 2;
		gbcbadanie.gridy = 0;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
    	badanie.add(kalendarz, gbcbadanie);
    	rightNow = Calendar.getInstance();
		
		lwaga = new JLabel("Waga: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 1;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(lwaga, gbcbadanie);
		
		twaga = new JNumberTextField();
		gbcbadanie.gridx = 2;
		gbcdanePacjenta.gridy = 1;
		gbcbadanie.gridwidth = 2;
		twaga.setPreferredSize(new Dimension(165, 20));
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(twaga, gbcbadanie);
		
		lbmi = new JLabel("BMI: ");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 2;
		gbcbadanie.gridwidth = 2;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(lbmi, gbcbadanie);
		
		tbmi = new JNumberTextField();
		gbcbadanie.gridx = 2;
		gbcdanePacjenta.gridy = 2;
		gbcbadanie.gridwidth = 2;
		tbmi.setPreferredSize(new Dimension(165, 20));
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(tbmi, gbcbadanie);
		
		bbadanieZapisz = new JButton("Zapisz");
		gbcbadanie.gridx = 0;
		gbcbadanie.gridy = 4;
		gbcbadanie.gridwidth = 1;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(bbadanieZapisz, gbcbadanie);
		
		bbadanieAnuluj = new JButton("Anuluj");
		gbcbadanie.gridx = 1;
		gbcbadanie.gridy = 4;
		gbcbadanie.gridwidth = 1;
		gbcbadanie.fill = GridBagConstraints.HORIZONTAL;
		badanie.add(bbadanieAnuluj, gbcbadanie);
		
		bbadanieZapisz.addActionListener(this);
		bbadanieAnuluj.addActionListener(this);
		
		//---------------------------------------LISTA PACJENTOW------------------------------------------
		
		listaPacjentow = new JPanel(); 
		BorderLayout borderLayout = new BorderLayout();
		listaPacjentow.setLayout(borderLayout);
		listaPacjentow1 = new JPanel();
		listaPacjentow1.setBackground(Color.white);
		listaPacjentow2 = new JPanel();
		listaPacjentow2.setBackground(Color.white);
		borderLayout.addLayoutComponent(listaPacjentow1, BorderLayout.NORTH);
		listaPacjentow.add(listaPacjentow1);
		borderLayout.addLayoutComponent(listaPacjentow2, BorderLayout.SOUTH);
		listaPacjentow.add(listaPacjentow2);

		listaPacjentow.setBorder(BorderFactory.createTitledBorder("Lista Pacjentów"));
		listaPacjentow.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(listaPacjentow, gbc);
		
		modelTabeli=new DefaultTableModel();
		tTabela=new JTable(modelTabeli);
		modelTabeli.addColumn("Imiê");
		modelTabeli.addColumn("Nazwisko");
		modelTabeli.addColumn("PESEL");
		
		listaPacjentow1.add(tTabela);
		tTabela.setBackground(Color.white);
		tTabela.setFillsViewportHeight(true);
		JScrollPane jsp = new JScrollPane(tTabela);
		jsp.setPreferredSize(new Dimension(325,200));
		jsp.setViewportView(tTabela);
		listaPacjentow1.add(jsp);
		
		listaPacjentow2.setLayout(new BorderLayout());
		JPanel przyciski = new JPanel();
		bdodaj = new JButton("Dodaj");
		busun = new JButton("Usuñ");
		przyciski.add(bdodaj);
		przyciski.add(busun);
		przyciski.setBackground(Color.white);
		listaPacjentow2.add(przyciski, BorderLayout.WEST);
				
		bdodaj.addActionListener(this);
		busun.addActionListener(this);
		
		//---------------------------------------------------WYKRES-------------------------------------------
		
		wykresBadan = new JPanel(); 
		wykresBadan.setBorder(BorderFactory.createTitledBorder("Wykres badañ pacjenta"));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.VERTICAL;
		wykresBadan.setBackground(Color.white);
		add(wykresBadan, gbc);
		
		wykresBadan.setLayout(new GridBagLayout()); 
		gbcwykresBadan.insets = new Insets(5,5,5,5);
		
        JFreeChart chart = ChartFactory.createLineChart("Wykres badañ", "Data", "Wartoœæ", ds, PlotOrientation.VERTICAL, true, true, false);
        cp = new ChartPanel(chart);
        
        gbcwykresBadan.gridx = 0;
        gbcwykresBadan.gridy = 0;
        gbcwykresBadan.gridwidth = 4;
        gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
        wykresBadan.add(cp, gbcwykresBadan);
        
        cwykres= new JComboBox <String> (opcjeWykres);
        gbcwykresBadan.gridx = 0;
        gbcwykresBadan.gridy = 1;
        gbcwykresBadan.gridwidth = 2;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		cwykres.setBackground(Color.white);
		cwykres.setPreferredSize(new Dimension(165, 20));
		wykresBadan.add(cwykres, gbcwykresBadan);
        
		lmin = new JLabel("Wartoœæ minimalna: ");
		gbcwykresBadan.gridx = 2;
		gbcwykresBadan.gridy = 1;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lmin, gbcwykresBadan);
		
		lmin2 = new JLabel("...................");
		gbcwykresBadan.gridx = 3;
		gbcwykresBadan.gridy = 1;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lmin2, gbcwykresBadan);
		
		lmax = new JLabel("Wartoœæ maksymalna: ");
		gbcwykresBadan.gridx = 2;
		gbcwykresBadan.gridy = 2;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lmax, gbcwykresBadan);
		
		lmax2 = new JLabel("...................");
		gbcwykresBadan.gridx = 3;
		gbcwykresBadan.gridy = 2;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lmax2, gbcwykresBadan);
		
		lsrednia = new JLabel("Wartoœæ œrednia: ");
		gbcwykresBadan.gridx = 2;
		gbcwykresBadan.gridy = 3;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lsrednia, gbcwykresBadan);
		
		lsrednia2 = new JLabel("...................");
		gbcwykresBadan.gridx = 3;
		gbcwykresBadan.gridy = 3;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		wykresBadan.add(lsrednia2, gbcwykresBadan);
		
		cwykres.addActionListener(this);
		
		//----------------------------- DANE WYBRANEGO PACJENTA--------------------------------------------
		
		daneWybrPacj = new JPanel(); 
		daneWybrPacj.setBorder(BorderFactory.createTitledBorder("Dane Wybranego Pacjenta"));
		daneWybrPacj.setPreferredSize(new Dimension(350,150));
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		daneWybrPacj.setBackground(Color.white);
		add(daneWybrPacj, gbc);
				
		daneWybrPacj.setLayout(new GridBagLayout()); 
		gbcdaneWybrPacj.insets = new Insets(5,5,5,5);
		
		limiepacjenta = new JLabel("Imiê pacjenta: ");
		gbcdaneWybrPacj.gridx = 0;
		gbcdaneWybrPacj.gridy = 0;
		gbcdaneWybrPacj.gridwidth = 1;
		gbcdaneWybrPacj.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(limiepacjenta, gbcdaneWybrPacj);
		
		limiepacjenta2 = new JLabel(".............................................................");
		gbcdaneWybrPacj.gridx = 1;
		gbcdaneWybrPacj.gridy = 0;
		gbcdaneWybrPacj.gridwidth = 1;
		gbcdaneWybrPacj.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(limiepacjenta2, gbcdaneWybrPacj);
		
		lnazwiskopacjenta = new JLabel("Nazwisko pacjenta: ");
		gbcdaneWybrPacj.gridx = 0;
		gbcdaneWybrPacj.gridy = 1;
		gbcdaneWybrPacj.gridwidth = 1;
		gbcdaneWybrPacj.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(lnazwiskopacjenta, gbcdaneWybrPacj);
		
		lnazwiskopacjenta2 = new JLabel(".............................................................");
		gbcdaneWybrPacj.gridx = 1;
		gbcdaneWybrPacj.gridy = 1;
		gbcdaneWybrPacj.gridwidth = 1;
		gbcdaneWybrPacj.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(lnazwiskopacjenta2, gbcdaneWybrPacj);
		
		lpeselpacjenta = new JLabel("PESEL pacjenta: ");
		gbcwykresBadan.gridx = 0;
		gbcwykresBadan.gridy = 2;
		gbcwykresBadan.gridwidth = 1;
		gbcwykresBadan.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(lpeselpacjenta, gbcwykresBadan);
		
		lpeselpacjenta2 = new JLabel(".............................................................");
		gbcdaneWybrPacj.gridx = 1;
		gbcdaneWybrPacj.gridy = 2;
		gbcdaneWybrPacj.gridwidth = 1;
		gbcdaneWybrPacj.fill = GridBagConstraints.HORIZONTAL;
		daneWybrPacj.add(lpeselpacjenta2, gbcdaneWybrPacj);
		
		
		//-----------------------------------LISTA BADAN PACJENTA----------------------------------
		
		listaBadanPacjenta = new JPanel(); 
		BorderLayout borderLayout1 = new BorderLayout();
		listaBadanPacjenta.setLayout(borderLayout1);
		listaBadanPacjenta1 = new JPanel();
		listaBadanPacjenta1.setBackground(Color.white);
		listaBadanPacjenta2 = new JPanel();
		listaBadanPacjenta2.setBackground(Color.white);
		borderLayout1.addLayoutComponent(listaBadanPacjenta1, BorderLayout.NORTH);
		listaBadanPacjenta.add(listaBadanPacjenta1);
		borderLayout1.addLayoutComponent(listaBadanPacjenta2, BorderLayout.SOUTH);
		listaBadanPacjenta.add(listaBadanPacjenta2);

		listaBadanPacjenta.setBorder(BorderFactory.createTitledBorder("Lista Badañ Wybranego Pacjenta"));
		listaBadanPacjenta.setBackground(Color.white);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		add(listaBadanPacjenta, gbc);
		
		modelTabeliBad=new DefaultTableModel();
		tTabelaBad=new JTable(modelTabeliBad);
		modelTabeliBad.addColumn("Data");
		modelTabeliBad.addColumn("Waga");
		modelTabeliBad.addColumn("BMI");
		
		listaBadanPacjenta1.add(tTabelaBad);
		tTabelaBad.setBackground(Color.white);
		tTabelaBad.setFillsViewportHeight(true);
		JScrollPane jsp1 = new JScrollPane(tTabelaBad);
		jsp1.setPreferredSize(new Dimension(325,350));
		jsp1.setViewportView(tTabelaBad);
		listaBadanPacjenta1.add(jsp1);
		
		listaBadanPacjenta2.setLayout(new BorderLayout());
		JPanel przyciski1 = new JPanel();
		busunbadanie = new JButton("Usuñ badanie");
		przyciski1.add(busunbadanie);
		przyciski1.setBackground(Color.white);
		listaBadanPacjenta2.add(przyciski1, BorderLayout.WEST);

		busunbadanie.addActionListener(this);
		
		pack();
		setResizable(false);
		
		onInit();
		
		tTabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(tTabela.getSelectedRow() < 0) {
					return;
				}
				
				int wiersz = tTabela.getSelectedRow();
				String selectedImie = tTabela.getModel().getValueAt(wiersz, 0).toString();
				limiepacjenta2.setText(selectedImie);
				String selectedNazwisko = tTabela.getModel().getValueAt(wiersz, 1).toString();
				lnazwiskopacjenta2.setText(selectedNazwisko);
				String selectedPeselPacj = tTabela.getModel().getValueAt(wiersz, 2).toString();
				lpeselpacjenta2.setText(selectedPeselPacj);
				long selPesel = Long.parseLong(selectedPeselPacj);
				cwykres.setSelectedIndex(0);
				
				if (tTabela.isRowSelected(wiersz) == true) { 			//jeœli wiersz w liœcie pacjentów jest zaznaczony
					
					ArrayList<badanie> listaBadan = BazaDanych.pacjentBadanie(selPesel);
					DefaultTableModel model = (DefaultTableModel) tTabelaBad.getModel();
					model.setRowCount(0);
			    	Object[] kol = new Object[3];
			    	for(int i=0 ; i<listaBadan.size(); i++) {
			    		kol[0] = listaBadan.get(i).getData();
			    		kol[1] = listaBadan.get(i).getWaga();
			    		kol[2] = listaBadan.get(i).getBMI();
			    		modelTabeliBad.addRow(kol);
			    	}
			    	ds.clear();
					ArrayList<badanie> badanieDane = BazaDanych.pacjentBadanie(selPesel);
					if (cwykres.getSelectedItem().toString() == " ") {
				    	ds.clear();
				    	lmin2.setText(" ");
				    	lmax2.setText(" ");
				    	lsrednia2.setText(" ");
				    	
					}
					if (cwykres.getSelectedItem().toString() == "Waga") {
						
						if(tTabelaBad.getSelectedRow() < 0) {
							twaga.setEnabled(true);
							tbmi.setEnabled(true);
							kalendarz.setEnabled(true);					
							return;
							}
						
						suma=0;
						srednia=0;
						min=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 1).toString());
						maks=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 1).toString());
						for(int i=0 ; i<badanieDane.size(); i++) {
				    		data = badanieDane.get(i).getData();
				    		dataFormat = df.format(data);
				    		
				    		String selectedWaga = tTabelaBad.getModel().getValueAt(i, 1).toString();
							long selWaga = Long.parseLong(selectedWaga);
				    		ds.addValue( selWaga , "Waga" , dataFormat );
				    		suma=suma+selWaga;
				    		if (min>selWaga) {
				    			min=selWaga;
				    		}
				    		if (maks<selWaga) {
				    			maks=selWaga;
				    		}
				    	}
						srednia=suma/badanieDane.size();
						lsrednia2.setText(""+ srednia);
						lmin2.setText(""+min);
						lmax2.setText(""+maks);
					}
					if (cwykres.getSelectedItem().toString() == "BMI") {
						
						if(tTabelaBad.getSelectedRow() < 0) {
							twaga.setEnabled(true);
							tbmi.setEnabled(true);
							kalendarz.setEnabled(true);					
							return;
							}
						
						suma=0;
						srednia=0;
						min=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 2).toString());
						maks=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 2).toString());
						for(int i=0 ; i<badanieDane.size(); i++) {
				    		data = badanieDane.get(i).getData();
				    		dataFormat = df.format(data);
							String selectedBMI = tTabelaBad.getModel().getValueAt(i, 2).toString();
							long selBMI = Long.parseLong(selectedBMI);
				    		ds.addValue( selBMI , "BMI" , dataFormat );
				    		suma=suma+selBMI;
				    		if (min>selBMI) {
				    			min=selBMI;
				    		}
				    		if (maks<selBMI) {
				    			maks=selBMI;
				    		}
				    	}
						srednia=suma/badanieDane.size();
						lsrednia2.setText(""+ srednia);
						lmin2.setText(""+min);
						lmax2.setText(""+maks);
					}

					lmin2.setText("...................");
					lmax2.setText("...................");
					lsrednia2.setText("...................");
					timie.setEnabled(false);
					tnazwisko.setEnabled(false);
					tpesel.setEnabled(false);
					twaga.setEnabled(true);
					tbmi.setEnabled(true);
					kalendarz.setEnabled(true);
					cwykres.setEnabled(true);
					return;
				}
			}
		});
	}
	
	private void onInit() { 			//funkcja dzia³aj¹ca przy w³¹czaniu programu
		pokazPacjenta();
		timie.setEnabled(false);
		tnazwisko.setEnabled(false);
		tpesel.setEnabled(false);
		twaga.setEnabled(false);
		tbmi.setEnabled(false);	
		kalendarz.setEnabled(false);
		cwykres.setEnabled(false);
	}
	
	public class JNumberTextField extends JTextField {		//pole numeryczne
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
	    public void processKeyEvent(KeyEvent ev) {
	        if (Character.isDigit(ev.getKeyChar())) {
	            super.processKeyEvent(ev);
	        }
	        if(ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
	        {  
	            super.processKeyEvent(ev);
	        }
	        ev.consume();
	        return;
	    }
	    public Long getNumber() {
	        Long result = null;
	        String text = getText();
	        if (text != null && !"".equals(text)) {
	            result = Long.valueOf(text);
	        }
	        return result;
	    }
	}
	
	//-------------------------------ACTION PERFORMED-------------------------------------------
	
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();
		
		if (source == bdodaj) {
			DefaultTableModel modelBad = (DefaultTableModel) tTabelaBad.getModel();
			modelBad.setRowCount(0);
			timie.setEnabled(true);
			tnazwisko.setEnabled(true);
			tpesel.setEnabled(true);
			tbmi.setEnabled(true);
			twaga.setEnabled(true);
			kalendarz.setEnabled(true);
			tTabela.clearSelection();
			limiepacjenta2.setText(".............................................................");
			lnazwiskopacjenta2.setText(".............................................................");
			lpeselpacjenta2.setText(".............................................................");
			lmin2.setText("...................");
			lmax2.setText("...................");
			lsrednia2.setText("...................");
		}
		
		
			if (source == busun) {
				int wiersz = tTabela.getSelectedRow();
				if(tTabela.isRowSelected(wiersz)) {
				
					String selectedPesel = tTabela.getModel().getValueAt(wiersz, 2).toString();
					long selPesel = Long.parseLong(selectedPesel);
					
					BazaDanych.UsunPacjenta(selPesel);
					BazaDanych.UsunBadanie(selPesel);
					
					DefaultTableModel model = (DefaultTableModel) tTabela.getModel();
					model.setRowCount(0);
					pokazPacjenta();
					DefaultTableModel modelBad = (DefaultTableModel) tTabelaBad.getModel();
					modelBad.setRowCount(0);
					pokazBadanie(selPesel);
				}else {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Zaznacz wiersz, który chcesz usun¹æ.");
					return;
				}
				
				timie.setText("");
				tnazwisko.setText("");
				tpesel.setText("");
				tbmi.setText("");
				twaga.setText("");
				kalendarz.setDate(rightNow.getTime());
					
				timie.setEnabled(false);
				tnazwisko.setEnabled(false);
				tpesel.setEnabled(false);
				kalendarz.setEnabled(false);
				twaga.setEnabled(false);
				tbmi.setEnabled(false);	
				return;
				
			}
			
		if (source == bzapisz) {
			
			if (timie.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz imiê.");
				return;
			}
			if (timie.getText().contains("1")||timie.getText().contains("2")||timie.getText().contains("3")||timie.getText().contains("4")||timie.getText().contains("5")||timie.getText().contains("6")||timie.getText().contains("7")||timie.getText().contains("8")||timie.getText().contains("9")||timie.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Imiê nie powinno zawieraæ cyfr.");
				return;
			}
			if (tnazwisko.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz nazwisko.");
				return;
			}
			if (tnazwisko.getText().contains("1")||tnazwisko.getText().contains("2")||tnazwisko.getText().contains("3")||tnazwisko.getText().contains("4")||tnazwisko.getText().contains("5")||tnazwisko.getText().contains("6")||tnazwisko.getText().contains("7")||tnazwisko.getText().contains("8")||tnazwisko.getText().contains("9")||tnazwisko.getText().contains("0")) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Nazwisko nie powinno zawieraæ cyfr.");
				return;
			}
			if (tpesel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz PESEL.");
				return;
			}
			if (tpesel.getText().length()< 11) { 
				JOptionPane.showMessageDialog(new JInternalFrame(), "Pesel jest zbyt krótki.");
				return;
			}
			
			imie = timie.getText();
			nazwisko = tnazwisko.getText();
			pesel = tpesel.getText().toString();
			
			peseldb = Long.parseLong(pesel);
			
			if (BazaDanych.SprawdzPesel(peseldb) == false) { //jeœli pesel w bazie danych jeszcze nie istnieje - dodawanie pacjenta
				BazaDanych.DodajPacjenta(imie, nazwisko, peseldb);
				modelTabeli.addRow(new Object[]{imie, nazwisko, pesel});
				
				timie.setText("");
				tnazwisko.setText("");
				tpesel.setText("");
				tbmi.setText("");
				twaga.setText("");
				kalendarz.setDate(rightNow.getTime());
				
				timie.setEnabled(false);
				tnazwisko.setEnabled(false);
				tpesel.setEnabled(false);
				kalendarz.setEnabled(false);
				twaga.setEnabled(false);
				tbmi.setEnabled(false);	
			} else {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Taki pacjent ju¿ istnieje.");
				timie.setText("");
				tnazwisko.setText("");
				tpesel.setText("");
				tbmi.setText("");
				twaga.setText("");
				kalendarz.setDate(rightNow.getTime());
					
				timie.setEnabled(false);
				tnazwisko.setEnabled(false);
				tpesel.setEnabled(false);
				kalendarz.setEnabled(false);
				twaga.setEnabled(false);
				tbmi.setEnabled(false);	
				return;
			}
			
			return;
		}
		
		if (source == banuluj) {
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			return;
		}
		
		if (source == bbadanieZapisz) {
			
			int wiersz = tTabela.getSelectedRow();
			if(tTabela.isRowSelected(wiersz) == false) { //jeœli ¿aden wiersz z listy pacjentów nie jest zaznaczony - dodanie nowego pacjenta wraz z badaniem
				if (timie.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz imiê.");
					return;
				}
				if (timie.getText().contains("1")||timie.getText().contains("2")||timie.getText().contains("3")||timie.getText().contains("4")||timie.getText().contains("5")||timie.getText().contains("6")||timie.getText().contains("7")||timie.getText().contains("8")||timie.getText().contains("9")||timie.getText().contains("0")) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Imiê nie powinno zawieraæ cyfr.");
					return;
				}
				if (tnazwisko.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz nazwisko.");
					return;
				}
				if (tnazwisko.getText().contains("1")||tnazwisko.getText().contains("2")||tnazwisko.getText().contains("3")||tnazwisko.getText().contains("4")||tnazwisko.getText().contains("5")||tnazwisko.getText().contains("6")||tnazwisko.getText().contains("7")||tnazwisko.getText().contains("8")||tnazwisko.getText().contains("9")||tnazwisko.getText().contains("0")) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Nazwisko nie powinno zawieraæ cyfr.");
					return;
				}
				if (tpesel.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz PESEL.");
					return;
				}
				if (twaga.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz wagê pacjenta.");
					return;
				}
				if (tbmi.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz wartoœæ wskaznika BMI pacjenta.");
					return;
				}
				data = kalendarz.getDate();
				data2 = (long) (data.getTime());
				waga = twaga.getText();
				int waga2 = Integer.parseInt(waga);
				bmi = tbmi.getText();
				int bmi2 = Integer.parseInt(bmi);
				pesel = tpesel.getText().toString();
				peseldb = Long.parseLong(pesel);
				BazaDanych.DodajBadanie(peseldb, data2, waga2, bmi2);
				
				imie = timie.getText();
				nazwisko = tnazwisko.getText();
				pesel = tpesel.getText().toString();
				
				peseldb = Long.parseLong(pesel);
				
				if (BazaDanych.SprawdzPesel(peseldb) == false) { //sprawdzenie czy mo¿na dodaæ nowego pacjenta
					BazaDanych.DodajPacjenta(imie, nazwisko, peseldb);
					modelTabeli.addRow(new Object[]{imie, nazwisko, pesel});
					
				} else {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Taki pacjent ju¿ istnieje.");
					timie.setText("");
					tnazwisko.setText("");
					tpesel.setText("");
					tbmi.setText("");
					twaga.setText("");
					kalendarz.setDate(rightNow.getTime());
						
					timie.setEnabled(false);
					tnazwisko.setEnabled(false);
					tpesel.setEnabled(false);
					kalendarz.setEnabled(false);
					twaga.setEnabled(false);
					tbmi.setEnabled(false);	
					return;
				}
				timie.setText("");
				tnazwisko.setText("");
				tpesel.setText("");
				tbmi.setText("");
				twaga.setText("");
				kalendarz.setDate(rightNow.getTime());
					
				timie.setEnabled(false);
				tnazwisko.setEnabled(false);
				tpesel.setEnabled(false);
				kalendarz.setEnabled(false);
				twaga.setEnabled(false);
				tbmi.setEnabled(false);	
				return;
			}
			else { //jeœli któryœ pacjent z listy jest zaznaczony
				if (twaga.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz wagê pacjenta.");
					return;
				}
				if (tbmi.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wpisz wartoœæ wskaznika BMI pacjenta.");
					return;
				}
				data = kalendarz.getDate();
				data2 = (long) (data.getTime());
				waga = twaga.getText();
				int waga2 = Integer.parseInt(waga);
				bmi = tbmi.getText();
				int bmi2 = Integer.parseInt(bmi);
				String selectedPesel = tTabela.getModel().getValueAt(wiersz, 2).toString();
				long selPesel2 = Long.parseLong(selectedPesel);
				
				ArrayList<badanie> badanie = BazaDanych.pacjentBadanie(selPesel2);
				modelTabeliBad = (DefaultTableModel)tTabelaBad.getModel();
				boolean flagaPowtorzeniaDaty = false;
		    	for(int i=0 ; i<badanie.size(); i++) {
		    			 if (df.format(badanie.get(i).getData()).toString().equals(df.format(data))) {
		    				 flagaPowtorzeniaDaty = true;	//oznaczenie, ¿e taka data ju¿ wyst¹pi³a
		    				 break;
		    			 }
		    	}
				
				if (flagaPowtorzeniaDaty == false) {	//sprawdzenie czy istnieje juz badanie dla tej daty
					BazaDanych.DodajBadanie(selPesel2, data2, waga2, bmi2);
					tbmi.setText("");
					twaga.setText("");
					kalendarz.setDate(rightNow.getTime());
					dataFormat = df.format(data);
					modelTabeliBad.addRow(new Object[]{dataFormat, waga2, bmi2});
				} else {
					JOptionPane.showMessageDialog(new JInternalFrame(), "Wybranego dnia badanie ju¿ by³o wykonane - usuñ poprzednie badanie w celu wprowadzenia poprawki.");
					timie.setText("");
					tnazwisko.setText("");
					tpesel.setText("");
					tbmi.setText("");
					twaga.setText("");
					kalendarz.setDate(rightNow.getTime());
						
					timie.setEnabled(false);
					tnazwisko.setEnabled(false);
					tpesel.setEnabled(false);
					kalendarz.setEnabled(false);
					twaga.setEnabled(false);
					tbmi.setEnabled(false);	
					return;
				}
			}		
			int wierszPacjent = tTabela.getSelectedRow();
			ds.clear();
			String selectedPesel = tTabela.getModel().getValueAt(wierszPacjent, 2).toString();
			long selPesel = Long.parseLong(selectedPesel);
			ArrayList<badanie> badanieDane = BazaDanych.pacjentBadanie(selPesel);
			if (cwykres.getSelectedItem().toString() == " ") {
		    	ds.clear();
			}
			if (cwykres.getSelectedItem().toString() == "Waga") {
				for(int i=0 ; i<badanieDane.size(); i++) {
		    		data = badanieDane.get(i).getData();
		    		dataFormat = df.format(data);
		    		String selectedWaga = tTabelaBad.getModel().getValueAt(i, 1).toString();
					long selWaga = Long.parseLong(selectedWaga);
		    		ds.addValue( selWaga , "Waga" , dataFormat );
		    	}
			}
			if (cwykres.getSelectedItem().toString() == "BMI") {
				for(int i=0 ; i<badanieDane.size(); i++) {
		    		data = badanieDane.get(i).getData();
		    		dataFormat = df.format(data);
					String selectedBMI = tTabelaBad.getModel().getValueAt(i, 2).toString();
					long selBMI = Long.parseLong(selectedBMI);
		    		ds.addValue( selBMI , "BMI" , dataFormat );
		    	}
			}
			
		}
		
		if (source == bbadanieAnuluj) {
			tbmi.setText("");
			twaga.setText("");
			kalendarz.setDate(rightNow.getTime());
			return;
		}
		
		if (source == busunbadanie) {
			int wierszPacjent = tTabela.getSelectedRow();
			int wierszBadanie = tTabelaBad.getSelectedRow();
			if(tTabela.isRowSelected(wierszPacjent) && tTabelaBad.isRowSelected(wierszBadanie)) {
			
				String selectedPesel = tTabela.getModel().getValueAt(wierszPacjent, 2).toString();
				long selPesel = Long.parseLong(selectedPesel);
				String selectedWaga = tTabelaBad.getModel().getValueAt(wierszBadanie, 1).toString();
				long selWaga = Long.parseLong(selectedWaga);
				
				BazaDanych.UsunBadanie(selPesel, selWaga);
				
				DefaultTableModel model = (DefaultTableModel) tTabelaBad.getModel();
				model.setRowCount(model.getRowCount()-1);
				pokazBadanie(selPesel);
			}else {
				JOptionPane.showMessageDialog(new JInternalFrame(), "Zaznacz wiersz, który chcesz usun¹æ.");
				return;
			}
			ds.clear();
			String selectedPesel = tTabela.getModel().getValueAt(wierszPacjent, 2).toString();
			long selPesel = Long.parseLong(selectedPesel);
			ArrayList<badanie> badanieDane = BazaDanych.pacjentBadanie(selPesel);
			if (cwykres.getSelectedItem().toString() == " ") {
		    	ds.clear();
			}
			if (cwykres.getSelectedItem().toString() == "Waga") {
				for(int i=0 ; i<badanieDane.size(); i++) {
		    		data = badanieDane.get(i).getData();
		    		dataFormat = df.format(data);
		    		String selectedWaga = tTabelaBad.getModel().getValueAt(i, 1).toString();
					long selWaga = Long.parseLong(selectedWaga);
		    		ds.addValue( selWaga , "Waga" , dataFormat );
		    	}
			}
			if (cwykres.getSelectedItem().toString() == "BMI") {
				for(int i=0 ; i<badanieDane.size(); i++) {
		    		data = badanieDane.get(i).getData();
		    		dataFormat = df.format(data);
					String selectedBMI = tTabelaBad.getModel().getValueAt(i, 2).toString();
					long selBMI = Long.parseLong(selectedBMI);
		    		ds.addValue( selBMI , "BMI" , dataFormat );
		    	}
			}
			
			timie.setText("");
			tnazwisko.setText("");
			tpesel.setText("");
			tbmi.setText("");
			twaga.setText("");
			kalendarz.setDate(rightNow.getTime());
				
			timie.setEnabled(false);
			tnazwisko.setEnabled(false);
			tpesel.setEnabled(false);
			kalendarz.setEnabled(true);
			twaga.setEnabled(true);
			tbmi.setEnabled(true);	
			return;
		}
		
		if (source == cwykres) {
			int wierszPacjent = tTabela.getSelectedRow();
			ds.clear();
			if(tTabela.isRowSelected(wierszPacjent)) {
				String selectedPesel = tTabela.getModel().getValueAt(wierszPacjent, 2).toString();
				long selPesel = Long.parseLong(selectedPesel);
				ArrayList<badanie> badanieDane = BazaDanych.pacjentBadanie(selPesel);
				if (cwykres.getSelectedItem().toString() == " ") {
					ds.clear();
			    	lmin2.setText(" ");
			    	lmax2.setText(" ");
			    	lsrednia2.setText(" ");
				}
				
				int wierszBadanie = tTabelaBad.getRowCount();
				
				if (wierszBadanie == 0) {
					return;
				}
					
					if (cwykres.getSelectedItem().toString() == "Waga") {
					suma=0;
					srednia=0;
					min=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 1).toString());
					maks=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 1).toString());
					for(int i=0 ; i<badanieDane.size(); i++) {
			    		data = badanieDane.get(i).getData();
			    		dataFormat = df.format(data);
			    		String selectedWaga = tTabelaBad.getModel().getValueAt(i, 1).toString();
						long selWaga = Long.parseLong(selectedWaga);
			    		ds.addValue( selWaga , "Waga" , dataFormat );
			    		suma=suma+selWaga;
			    		if (min>selWaga) {
			    			min=selWaga;
			    		}
			    		if (maks<selWaga) {
			    			maks=selWaga;
			    		}
			    	}
					srednia=suma/badanieDane.size();
					lsrednia2.setText(""+ srednia);
					lmin2.setText(""+min);
					lmax2.setText(""+maks);
				}
				if (cwykres.getSelectedItem().toString() == "BMI") {
					suma=0;
					srednia=0;
					min=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 2).toString());
					maks=Long.parseLong(tTabelaBad.getModel().getValueAt(0, 2).toString());
					for(int i=0 ; i<badanieDane.size(); i++) {
			    		data = badanieDane.get(i).getData();
			    		dataFormat = df.format(data);
						String selectedBMI = tTabelaBad.getModel().getValueAt(i, 2).toString();
						long selBMI = Long.parseLong(selectedBMI);
			    		ds.addValue( selBMI , "BMI" , dataFormat );
			    		suma=suma+selBMI;
			    		if (min>selBMI) {
			    			min=selBMI;
			    		}
			    		if (maks<selBMI) {
			    			maks=selBMI;
			    		}
			    	}
					srednia=suma/badanieDane.size();
					lsrednia2.setText(""+ srednia);
					lmin2.setText(""+min);
					lmax2.setText(""+maks);
					
				}
				
			}
			cp.getChart().getCategoryPlot().setDataset(ds);
			cp.getChart().fireChartChanged();
			cp.repaint();
			return;
			
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			projekt2 projekt2 = new projekt2();
			projekt2.setVisible(true);
			projekt2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	    });
		
	}

}

