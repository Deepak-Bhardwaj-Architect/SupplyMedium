package servlet.transconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.transconfig.CarrierData;
import core.transconfig.CurrencyData;
import core.transconfig.NAICSCodeData;
import core.transconfig.QuantityTypeData;
import ctrl.transconfig.TransConfigController;

/**
 * Servlet implementation class TransConfigServlet
 */
@WebServlet("/TransConfigServlet")
public class TransConfigServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransConfigServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		String transConfigType = request.getParameter( "TransConfigType" );

		TransConfigController transConfigCtrl = new TransConfigController( );

		TransConfigComposer transConfigJSONComposer = new TransConfigComposer( );

		String jsonStr = "";

		int result = 0;

		if( transConfigType.equals( "GetCarriers" ) ) // fetch all carriers
		{
			List<CarrierData> carrierDataList = new ArrayList<CarrierData>( );

			result = transConfigCtrl.getAllCarriers( carrierDataList );

			if( result == 9000 ) // Success
			{
				jsonStr = transConfigJSONComposer.composeCarriersJSON( carrierDataList );
			}
			else
			// Failed
			{
				jsonStr = transConfigJSONComposer.composeErrorMessage( result );
			}

			carrierDataList = null;
		}
		else if( transConfigType.equals( "GetCurrencies" ) ) // fetch all currencies
		{
			List<CurrencyData> currencyDataList = new ArrayList<CurrencyData>( );

			result = transConfigCtrl.getAllCurrencies( currencyDataList );

			if( result == 9010 ) // Success
			{
				jsonStr = transConfigJSONComposer.composeCurrencyJSON( currencyDataList );
			}
			else
			// Failed
			{
				jsonStr = transConfigJSONComposer.composeErrorMessage( result );
			}

			currencyDataList = null;
		}
		else if( transConfigType.equals( "QuantityType" ) ) // Fetch all quantity types
		{
			List<QuantityTypeData> quantityTypeDataList = new ArrayList<QuantityTypeData>( );

			result = transConfigCtrl.getAllQuantityType( quantityTypeDataList );

			if( result == 9020 ) // Success
			{
				jsonStr = transConfigJSONComposer.quantityTypeJSON( quantityTypeDataList );
			}
			else
			// Failed
			{
				jsonStr = transConfigJSONComposer.composeErrorMessage( result );
			}

			quantityTypeDataList = null;

		}
		else if( transConfigType.equals( "GetNAICSCodes" ) ) // Fetch all NAICS codes
		{
			List<NAICSCodeData> NAICSCodeDataList = new ArrayList<NAICSCodeData>( );

			result = transConfigCtrl.getAllNAICSCode( NAICSCodeDataList );

			if( result == 9030 ) // Success
			{
				jsonStr = transConfigJSONComposer.composeNAICSCodeJSON( NAICSCodeDataList );
			}
			else
			// Failed
			{
				jsonStr = transConfigJSONComposer.composeErrorMessage( result );
			}

			NAICSCodeDataList = null;
		}

		transConfigCtrl = null;

		transConfigJSONComposer = null;

		System.out.println( "jsonStr=" + jsonStr );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( jsonStr );
	}
}
