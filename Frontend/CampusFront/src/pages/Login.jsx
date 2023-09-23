import './Login.css'
import LoginForm from '../components/formlogin';
export default function Login(){
    return (
        <div className='main'>
            <div className='left-info'>
                <div className='bubble-colorful small'></div>
                <div className='bubble-colorful medium'></div>
                <div className='bubble-colorful big'></div>
                <div className='bubble-big-white'></div>
                <div className='info-heading'>
                <h2>Welcome to Bilko</h2>
                <p>Platform to socialize and trade</p>
                </div>
            </div>
            <div className='right-login-form'>
                <LoginForm/>
            </div>
        </div>
    );
}