package ctec.soundandvideo2.controller;

import android.app.Activity;
import android.os.Bundle;
import android.media.*;
import android.view.*;
import android.widget.*;

public class Sound extends Activity implements Runnable
{
	
	private Button startButton;
		private Button stopButton;
		private Button pauseButton;
		private Button videoButton;
		private MediaPlayer soundPlayer;
		private SeekBar soundSeekBar;
		private Thread soundThread;
		
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sound);
			
			startButton = (Button) findViewById(R.id.startButton);
			pauseButton = (Button) findViewById(R.id.pauseButton);
			stopButton = (Button)  findViewById(R.id.stopButton);
			soundSeekBar= (SeekBar) findViewById(R.id.soundSeekBar);
			soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.pomdeter);
			
			setupListeners(); 
			
			soundThread = new Thread(this);
			soundThread.start();
		}
		
		
		private void setupListeners()
		{
			startButton.setOnClickListener(new View.OnClickListener()
			{
			
				@Override
				public void onClick(View currentView)
				{
					soundPlayer.start();
					
				}
			});
			
			pauseButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View currentView)
				{
					soundPlayer.pause();
					
				}
			});
			
			stopButton.setOnClickListener(new View.OnClickListener()
			{
				
				public void onClick(View currentView)
				{
					soundPlayer.stop();
					soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.pomdeter);
				}
			});
			
			
			soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
			{
				
				public void onStopTrackingTouch(SeekBar seekBar)
				{}
				public void onStartTrackingTouch(SeekBar seekBar)
				{}
				
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
				{
					if(fromUser)
					{
						soundPlayer.seekTo(progress);
					}
				};
			
			
		public void run()
		{
			int currentPosition = 0;
			int soundTotal = soundPlayer.getDuration();
			soundSeekBar.setMax(soundTotal);
			
			while(soundPlayer !=null && currentPosition < soundTotal)
			{
				try
				{
					Thread.sleep(50);
					currentPosition = soundPlayer.getCurrentPosition();
				}
				catch(InterruptedException soundException)
				{
					return;
				}
				catch(Exception otherException)
				{
					return;
				}
				soundSeekBar.setProgress(currentPosition);
			}
			
			
			}
		}
	 }
		
}


		